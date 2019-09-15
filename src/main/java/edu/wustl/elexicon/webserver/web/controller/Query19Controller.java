package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.TempNonWordTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Query19Controller extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(Query19Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private TempNonWordTableRepository tempNonWordTableRepository;
    private final Mailer mailer;
    private final CsvWriter csvWriter;

    public Query19Controller(TempNonWordTableRepository tempNonWordTableRepository, Mailer mailer, CsvWriter csvWriter) {
        this.tempNonWordTableRepository = tempNonWordTableRepository;
        this.csvWriter = csvWriter;
        this.mailer = mailer;
    }

    @PostMapping(value = "/query19/query19do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, HttpSession session) {
        String trxId = initializeSession(log, session);
        List<String> fields = formData.get("field") == null ? new ArrayList<>() : formData.get("field");
        List<String> distubution = formData.get("dist");
        session.setAttribute("FIELDS", fields);
        session.setAttribute("DISTRIBUTION", distubution);
        return formData.get("list").contains("tlist") ? "query19/query19list" : "query19/query19file";
    }

    @PostMapping(value = "/query19/query19filedo")
    public String processFile(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Process File" );
        if (file.isEmpty()) {
            model.addAttribute("errorMessage", "ERROR:  You must supply a file!");
            return "query19/query19file";
        }
        List<String> words = parseFile(log, file);
        return processNonWords(model, session, trxId, words);
    }

    @PostMapping(value = "/query19/query19listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Process List" );
        String wordlist = formData.get("wordlist").get(0);
        List<String> words = parseString(wordlist);

        return processNonWords(model, session, trxId, words);
    }

    private String processNonWords(Model model, HttpSession session, String trxId, List<String> words) {
        log.info("Session Id: " + trxId + " WordsSize: " + words.size());
        List<String> fields = (List<String>) session.getAttribute("FIELDS");
        List<Map<String, String>> query = tempNonWordTableRepository.get(trxId, words, fields);
        log.info("Session Id: " + trxId + " QuerySize: " + query.size());
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query19/query19.html");
            return "errorback";
        }
        session.setAttribute("nwItems", query);
        List<String> distubution = (List<String>) session.getAttribute("DISTRIBUTION");
        if (query.size() > maxHtmlResultSet || distubution.contains("email")) {
            return "query19/emailresponse";
        }
        model.addAttribute("nwItems", query);
        model.addAttribute("nwItemCount", query.size());
        addButtonFlags(model);
        return "query19/query19final";
    }

    @PostMapping(value = "/query19/query19domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Processing email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query19/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        final List<Map<String, String>> items = (List<Map<String, String>>) session.getAttribute("nwItems");
        if (items != null) {
            model.addAttribute("trxId", trxId);
            try {
                String csv = csvWriter.writeCsv(items);
                Map<String, String> attachments = new HashMap<>();
                attachments.put("NonWord.csv", csv);
                mailer.sendMessage(trxId, attachments, emailAddress);
            } catch (IOException e) {
                log.error("error: ", e);
            }
        }
        return "query19/query19doemail";
    }

    private void addButtonFlags(Model model) {
        model.addAttribute("orthoButtonFlag", Boolean.TRUE);
    }





}
