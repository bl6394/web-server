package edu.wustl.elexicon.webserver.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Query13Controller {
    
    private ItemRepository itemRepository;

    public Query13Controller(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @PostMapping (value = "/query13/query13do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        System.out.println(formData);
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        List<Map<String, String>> query = itemRepository.get(formData.get("field"), targetDb, formData.get("constraints"));
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", formData.get("scope").contains("RESELP") ? "Restricted" : "Complete" );
        addButtonFlags(formData, model);
        if (query.size() > 2000){
            model.addAttribute("errorMessage", "You query returned " + query.size() + " results and is too large to display." );
            model.addAttribute("errorBackLink", "/query13/query13.html");
            return "errorback";
        }
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query13/query13.html");
            return "errorback";
        }
        return "query13do";
    }

    private void addButtonFlags(@RequestBody MultiValueMap<String, String> formData, Model model) {
        model.addAttribute("orthoButtonFlag", formData.get("OrthoBTN"));
        model.addAttribute("phonoButtonFlag", formData.get("PhonoBTN"));
        model.addAttribute("phonoHButtonFlag", formData.get("PhonoHBTN"));
        model.addAttribute("ogButtonFlag", formData.get("OGBTN"));
        model.addAttribute("oghButtonFlag", formData.get("OGHBTN"));
    }

}