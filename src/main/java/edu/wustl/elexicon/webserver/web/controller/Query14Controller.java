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
import java.nio.charset.Charset;

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
        String[] result = parse(file);
        tempTableRepository.get();
        return "query14/query14final";
    }

    private String[] parse(MultipartFile file) {
        String line;
        try {
            InputStream is = file.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals("/commercial/")) {
                        i++;
                        while (!words[i].equals("|")) {
                            System.out.print(words[i]);
                            i++; //Don't forget to check your index to be sure you are never out of bounds! Not done here.
                            //You can also remove the "/" caracter if needed.
                        }

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping(value = "/query14/query14do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        System.out.println(formData);
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        return formData.get("list").contains("tlist") ? "query14/query14list" : "query14/query14file";
    }


}
