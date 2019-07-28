package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.web.repository.TempTableRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Query14Controller {

    private TempTableRepository tempTableRepository;
    private Map<String, Object> sessionStore;

    public Query14Controller(TempTableRepository tempTableRepository, Map<String, Object> sessionStore) {
        this.tempTableRepository = tempTableRepository;
        this.sessionStore = sessionStore;
    }

    @PostMapping(value = "/query14/query14do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        List<String> fields = formData.get("field") == null ? new ArrayList<>() : formData.get("field");
        sessionStore.put("TARGET_DB", targetDb);
        sessionStore.put("FIELDS", fields);
        return formData.get("list").contains("tlist") ? "query14/query14list" : "query14/query14file";
    }

    @PostMapping(value = "/query14/query14filedo")
    public String processFile(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes, Model model) {
        List<String> words = parseFile(file);
        String targetDb = (String) sessionStore.get("TARGET_DB");
        List<String> fields = (List<String>) sessionStore.get("FIELDS");
        List<Map<String, String>> query = tempTableRepository.get(words, fields, targetDb);
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query14/query14.html");
            return "errorback";
        }
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", targetDb.equals("items") ? "Restricted" : "Complete");
        addButtonFlags(model);
        return "query14/query14final";
    }

    @PostMapping(value = "/query14/query14listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model) {
        String wordlist = formData.get("wordlist").get(0);
        List<String> words = parseString(wordlist);
        List<String> fields = (List<String>) sessionStore.get("FIELDS");
        String targetDb = (String) sessionStore.get("TARGET_DB");
        List<Map<String, String>> query = tempTableRepository.get(words, fields, targetDb);
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query14/query14.html");
            return "errorback";
        }
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", targetDb.equals("items") ? "Restricted" : "Complete");
        addButtonFlags(model);
        return "query14/query14final";
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
