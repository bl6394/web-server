package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.LexicalDataRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Query17Controller extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(Query17Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final LexicalDataRepository lexicalDataRepository;

    public Query17Controller(LexicalDataRepository lexicalDataRepository, Mailer mailer, CsvWriter csvWriter) {
        this.lexicalDataRepository = lexicalDataRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @PostMapping(value = "/query17/query17do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = initializeSession(log, session);
        int querySize = lexicalDataRepository.getSize(trxId, formData.get("field"), formData.get("constraints"));
        log.info("Session Id: " + trxId + " Pre Size Check: " + querySize);
        if ( querySize >= 100000){
            model.addAttribute("errorMessage", "You query generated " + querySize + " results!  Please add constraints...");
            model.addAttribute("errorBackLink", "/query17/query17.html");
            return "errorback";
        }
        List<Map<String, String>> query = lexicalDataRepository.get(trxId, formData.get("field"), formData.get("constraints"));
        log.info("Session Id: " + trxId + " QuerySize: " + query.size() );
        session.setAttribute("expData", query);
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("expData", query);
        model.addAttribute("expDataCount", query.size());
        addButtonsFlags(formData, model);
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query17/query17.html");
            return "errorback";
        }
        if (query.size() > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            return "query17/emailresponse";
        }
        return "query17/query17do";
    }

    @PostMapping(value = "/query17/query17domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Processing email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query17/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        final List<Map<String, String>> expData = (List<Map<String, String>>) session.getAttribute("expData");
        if (expData != null) {
            model.addAttribute("trxId", trxId);
            try {
                Map<String, String> attachments = new HashMap<>();
                String csv = csvWriter.writeCsv(expData);
                attachments.put("LexicalData.csv", csv);
                mailer.sendMessage(trxId, attachments, emailAddress);
            } catch (IOException e) {
                log.error("error: ", e);
            }
        }
        return "query17/query17doemail";
    }



}
