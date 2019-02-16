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

    public Query14Controller(TempTableRepository tempTableRepository) {
        this.tempTableRepository = tempTableRepository;
    }

    @PostMapping(value = "/query14/query14do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        return formData.get("list").contains("tlist") ? "query14/query14list" : "query14/query14file";
    }

    @PostMapping(value = "/query14/query14filedo")
    public String processFile(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes, Model model) {
        List<String> words = parseFile(file);
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("Length");
        fieldNames.add("Freq_HAL");
        fieldNames.add("Ortho_N");
        List<Map<String, String>> query = tempTableRepository.get(words, fieldNames, "item");
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", "Restricted" );
        addButtonFlags(model);
        return "query14/query14final";
    }

    @PostMapping(value = "/query14/query14listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model) {
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



}
