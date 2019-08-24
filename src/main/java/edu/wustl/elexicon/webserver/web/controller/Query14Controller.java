package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.domain.QueryDTO;
import edu.wustl.elexicon.webserver.web.repository.TempTableRepository;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class Query14Controller {

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private TempTableRepository tempTableRepository;
    private Map<String, Object> sessionStore;
    private final Mailer mailer;
    private final CsvWriter csvWriter;

    public Query14Controller(TempTableRepository tempTableRepository, Map<String, Object> sessionStore, Mailer mailer, CsvWriter csvWriter) {
        this.tempTableRepository = tempTableRepository;
        this.sessionStore = sessionStore;
        this.csvWriter = csvWriter;
        this.mailer = mailer;
    }

    @PostMapping(value = "/query14/query14do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        List<String> fields = formData.get("field") == null ? new ArrayList<>() : formData.get("field");
        List<String> distubution = formData.get("dist");
        sessionStore.put("TARGET_DB", targetDb);
        sessionStore.put("FIELDS", fields);
        sessionStore.put("DISTRIBUTION", distubution);
        return formData.get("list").contains("tlist") ? "query14/query14list" : "query14/query14file";
    }

    @PostMapping(value = "/query14/query14filedo")
    public String processFile(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
        List<String> words = parseFile(file);
        String targetDb = (String) sessionStore.get("TARGET_DB");
        List<String> fields = (List<String>) sessionStore.get("FIELDS");
        QueryDTO queryDTO = tempTableRepository.get(words, fields, targetDb);
        List<Map<String, String>> query = queryDTO.query;
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query14/query14.html");
            return "errorback";
        }
        session.setAttribute("items", query);
        List<String> distubution = (List<String>) sessionStore.get("DISTRIBUTION");
        if (query.size() > maxHtmlResultSet || distubution.contains("email") ) {
            return "query14/emailresponse";
        }
        model.addAttribute("items", query);
        model.addAttribute("submissionCount", words.size());
        model.addAttribute("notFound", queryDTO.notFound);
        model.addAttribute("summary", queryDTO.summary);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", targetDb.equals("item") ? "Restricted" : "Complete");
        addButtonFlags(model);
        return "query14/query14final";
    }

    @PostMapping(value = "/query14/query14listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String wordlist = formData.get("wordlist").get(0);
        List<String> words = parseString(wordlist);
        List<String> fields = (List<String>) sessionStore.get("FIELDS");
        String targetDb = (String) sessionStore.get("TARGET_DB");
        QueryDTO queryDTO = tempTableRepository.get(words, fields, targetDb);
        List<Map<String, String>> query = queryDTO.query;
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query14/query14.html");
            return "errorback";
        }
        session.setAttribute("items", query);
        List<String> distubution = (List<String>) sessionStore.get("DISTRIBUTION");
        if (query.size() > maxHtmlResultSet || distubution.contains("email") ) {
            return "query14/emailresponse";
        }
        model.addAttribute("items", query);
        model.addAttribute("submissionCount", words.size());
        model.addAttribute("notFound", queryDTO.notFound);
        model.addAttribute("summary", queryDTO.summary);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", targetDb.equals("item") ? "Restricted" : "Complete");
        addButtonFlags(model);
        return "query14/query14final";
    }

    @PostMapping(value = "/query14/query14domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String emailAddress = formData.getFirst("address");
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query14/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        final List<Map<String, String>> items = (List<Map<String, String>>) session.getAttribute("items");
        if (items != null) {
            String uuid = UUID.randomUUID().toString();
            model.addAttribute("trxId", uuid);
            try {
                String csv = csvWriter.writeCsv(uuid, items);
                mailer.sendMessage(uuid, csv, emailAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "query14/query14doemail";
    }

    private void addButtonFlags(Model model) {
        model.addAttribute("orthoButtonFlag", Boolean.TRUE);
        model.addAttribute("phonoButtonFlag", Boolean.TRUE);
        model.addAttribute("phonoHButtonFlag", Boolean.TRUE);
        model.addAttribute("ogButtonFlag", Boolean.TRUE);
        model.addAttribute("oghButtonFlag", Boolean.TRUE);
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
            e.printStackTrace();
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
