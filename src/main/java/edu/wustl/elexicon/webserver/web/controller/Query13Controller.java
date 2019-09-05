package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import edu.wustl.elexicon.webserver.web.repository.NeighborRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class Query13Controller {

    private final Logger log = LoggerFactory.getLogger(Query13Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final ItemRepository itemRepository;
    private final NeighborRepository neighborRepository;

    public Query13Controller(ItemRepository itemRepository, NeighborRepository neighborRepository, Mailer mailer, CsvWriter csvWriter) {
        this.itemRepository = itemRepository;
        this.neighborRepository = neighborRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @PostMapping(value = "/query13/query13do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = UUID.randomUUID().toString();
        session.setAttribute("TRX_ID", trxId);
        log.info("Session Id: " + trxId + " Starting Query 13" );
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        List<Map<String, String>> query = itemRepository.get(trxId, formData.get("field"), targetDb, formData.get("constraints"));
        log.info("Session Id: " + trxId + " Query 13 Size: " + query.size() );
        session.setAttribute("items", query);
        session.setAttribute("targetDb", targetDb);
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", formData.get("scope").contains("RESELP") ? "Restricted" : "Complete");
        addButtonFlags(formData, model);
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query13/query13.html");
            return "errorback";
        }
        if (query.size() > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            return "query13/emailresponse";
        }
        return "query13/query13do";
    }

    @PostMapping(value = "/query13/query13domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = (String) session.getAttribute("TRX_ID");
        log.info("Session Id: " + trxId + " Processing Query 13" );
        String emailAddress = formData.getFirst("address");
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query13/emailresponse";
        }
        log.info("Session Id: " + trxId + " Query 13 Email: " + emailAddress );
        model.addAttribute("emailAddress", emailAddress);
        final List<Map<String, String>> items = (List<Map<String, String>>) session.getAttribute("items");
        if (items != null) {
            model.addAttribute("trxId", trxId);
            try {
                Map<String, String> attachments = new HashMap<>();
                String itemsCsv = csvWriter.writeCsv(items);
                attachments.put("Items.csv", itemsCsv);
                List<Map<String, String>> allNeighbors = neighborRepository.getMany(convertItemsToWords(items), "neighbors", (String) session.getAttribute("targetDb"));
                String neighborhoodCsv = csvWriter.writeCsv(allNeighbors);
                attachments.put("Neighborhood.csv", neighborhoodCsv);
                mailer.sendMessage(trxId, attachments, emailAddress);
            } catch (IOException e) {
                log.error("error",  e);
            }
        }
        log.info("Session Id: " + trxId + " Finished Query 13" );
        return "query13/query13doemail";
    }

    private List<String> convertItemsToWords(List<Map<String, String>> items) {
        List<String> words = new ArrayList<>();
        for (Map<String,String> item: items){
            words.add(item.get("Word"));
        }
        return words;
    }


    private void addButtonFlags(@RequestBody MultiValueMap<String, String> formData, Model model) {
        if (formData.get("field") == null) {
            return;
        }
        model.addAttribute("orthoButtonFlag", formData.get("field").contains("OrthoBTN"));
        model.addAttribute("phonoButtonFlag", formData.get("field").contains("PhonoBTN"));
        model.addAttribute("phonoHButtonFlag", formData.get("field").contains("PhonoHBTN"));
        model.addAttribute("ogButtonFlag", formData.get("field").contains("OGBTN"));
        model.addAttribute("oghButtonFlag", formData.get("field").contains("OGHBTN"));
    }



}
