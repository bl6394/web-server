package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.TempNonWordTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Controller
public class Query16Controller {

    private final Logger log = LoggerFactory.getLogger(Query16Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private TempNonWordTableRepository tempNonWordTableRepository;
    private final Mailer mailer;
    private final CsvWriter csvWriter;

    public Query16Controller(TempNonWordTableRepository tempNonWordTableRepository,  Mailer mailer, CsvWriter csvWriter) {
        this.tempNonWordTableRepository = tempNonWordTableRepository;
        this.csvWriter = csvWriter;
        this.mailer = mailer;
    }

    @PostMapping(value = "/query16/query16do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = UUID.randomUUID().toString();
        session.setAttribute("TRX_ID", trxId);
        log.info("Session Id: " + trxId + " Starting" );
        List<String> fields = formData.get("field") == null ? new ArrayList<>() : formData.get("field");
        List<String> distubution = formData.get("dist");
        session.setAttribute("FIELDS", fields);
        session.setAttribute("DISTRIBUTION", distubution);
        return formData.get("list").contains("tlist") ? "query16/query16list" : "query16/query16file";
    }

    @PostMapping(value = "/query16/query16filedo")
    public String processFile(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        String trxId = (String) session.getAttribute("TRX_ID");
        log.info("Session Id: " + trxId + " Process File" );
        if (file.isEmpty()) {
            model.addAttribute("errorMessage", "ERROR:  You must supply a file!");
            return "query16/query16file";
        }
        List<String> words = parseFile(file);
        log.info("Session Id: " + trxId + " WordsSize: " + words.size() );
        List<String> fields = (List<String>) session.getAttribute("FIELDS");
        List<Map<String, String>> query = tempNonWordTableRepository.get(trxId, words, fields);
        log.info("Session Id: " + trxId + " QuerySize: " + query.size() );
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query16/query16.html");
            return "errorback";
        }
        session.setAttribute("nwItems", query);
        List<String> distubution = (List<String>) session.getAttribute("DISTRIBUTION");
        if (query.size() > maxHtmlResultSet || distubution.contains("email") ) {
            return "query16/emailresponse";
        }
        model.addAttribute("nwItems", query);
        model.addAttribute("nwItemCount", query.size());
        addButtonFlags(model);
        return "query16/query16final";
    }

    @PostMapping(value = "/query16/query16listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = (String) session.getAttribute("TRX_ID");
        log.info("Session Id: " + trxId + " Process List" );
        String wordlist = formData.get("wordlist").get(0);
        List<String> words = parseString(wordlist);
        log.info("Session Id: " + trxId + " WordsSize: " + words.size() );
        List<String> fields = (List<String>) session.getAttribute("FIELDS");
        List<Map<String, String>> query = tempNonWordTableRepository.get(trxId, words, fields);
        log.info("Session Id: " + trxId + " QuerySize: " + query.size() );
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query16/query16.html");
            return "errorback";
        }
        session.setAttribute("nwItems", query);
        List<String> distubution = (List<String>) session.getAttribute("DISTRIBUTION");
        if (query.size() > maxHtmlResultSet || distubution.contains("email") ) {
            return "query16/emailresponse";
        }
        model.addAttribute("nwItems", query);
        model.addAttribute("nwItemCount", query.size());
        addButtonFlags(model);
        return "query16/query16final";
    }

    @PostMapping(value = "/query16/query16domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = (String) session.getAttribute("TRX_ID");
        log.info("Session Id: " + trxId + " Processing Email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query16/emailresponse";
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
                log.error("error",  e);
            }
        }
        return "query16/query16doemail";
    }

    private void addButtonFlags(Model model) {
        model.addAttribute("orthoButtonFlag", Boolean.TRUE);
    }

    private List<String> parseFile(MultipartFile file) {
        List<String> words = new ArrayList<>();
        String line;
        try {
            InputStream is = file.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String cleanLine = line.replaceAll("[^A-Za-z\']", " ");
                String[] wordsInLine = cleanLine.split("\\s+");
                for (int i = 0; i < wordsInLine.length; i++) {
                    String cleanWord = wordsInLine[i].trim();
                    if (!cleanWord.isEmpty()) {
                        words.add(cleanWord);
                    }
                }
            }

        } catch (IOException e) {
            log.error("error",  e);
        }
        return words;
    }

    private List<String> parseString(String wordlist) {
        List<String> words = new ArrayList<>();
        String cleanLine = wordlist.replaceAll("[^A-Za-z\']", " ");
        String[] wordsInLine = cleanLine.split("\\s+");
        for (int i = 0; i < wordsInLine.length; i++) {
            String cleanWord = wordsInLine[i].trim();
            if (!cleanWord.isEmpty()) {
                words.add(cleanWord);
            }
        }
        return words;
    }


}
