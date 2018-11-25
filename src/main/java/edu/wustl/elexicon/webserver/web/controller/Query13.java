package edu.wustl.elexicon.webserver.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Query13 {

    @PostMapping (value = "/query13/query13do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("name", "Bjorn");
        return "/query13/query13do";
    }

}
