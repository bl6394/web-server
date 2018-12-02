package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class Query13 {
    
    @Autowired
    ItemRepository itemRepository;

    @PostMapping (value = "/query13/query13do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        List<Map<String, String>> query = itemRepository.get3(formData.get("field"));
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("orthoButtonFlag", formData.get("OrthoBTN"));
        return "/query13/query13do";
    }

}
