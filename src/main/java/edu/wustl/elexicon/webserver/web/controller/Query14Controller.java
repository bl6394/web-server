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

@Controller
public class Query14Controller {

    private TempTableRepository tempTableRepository;

    public Query14Controller(TempTableRepository tempTableRepository) {
        this.tempTableRepository = tempTableRepository;
    }

    @PostMapping(value = "/query14/query14listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model) {
        System.out.println(formData);
        return "query14/query14final";
    }

    @PostMapping(value = "/query14/query14filedo")
    public String processFile(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
        List<String> words = parseFile(file);
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("word");
        fieldNames.add("length");
        tempTableRepository.get(words,fieldNames, "item" );
        return "query14/query14final";
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
                        System.out.println(cleanWord);
                        words.add(cleanWord);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }


    @PostMapping(value = "/query14/query14do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        System.out.println(formData);
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        return formData.get("list").contains("tlist") ? "query14/query14list" : "query14/query14file";
    }


}
