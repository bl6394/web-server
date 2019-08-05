package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.TempNonWordTableRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class Query16Controller {

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
        List<String> fields = formData.get("field") == null ? new ArrayList<>() : formData.get("field");
        List<String> distubution = formData.get("dist");
        session.setAttribute("FIELDS", fields);
        session.setAttribute("DISTRIBUTION", distubution);
        return formData.get("list").contains("tlist") ? "query16/query16list" : "query16/query16file";
    }

    @PostMapping(value = "/query16/query16filedo")
    public String processFile(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        List<String> words = parseFile(file);
        List<String> fields = (List<String>) session.getAttribute("FIELDS");
        List<Map<String, String>> query = tempNonWordTableRepository.get(words, fields);
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
        String wordlist = formData.get("wordlist").get(0);
        List<String> words = parseString(wordlist);
        List<String> fields = (List<String>) session.getAttribute("FIELDS");
        List<Map<String, String>> query = tempNonWordTableRepository.get(words, fields);
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
        String emailAddress = formData.getFirst("address");
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query16/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        final List<Map<String, String>> items = (List<Map<String, String>>) session.getAttribute("nwItems");
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