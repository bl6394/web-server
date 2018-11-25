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
    public String greeting1(@RequestBody MultiValueMap<String, String> formData, Model model) {
        model.addAttribute("contraints", formData.get("constraints"));
        List<String> field = formData.get("field");
        model.addAttribute("field", field);
        model.addAttribute("name", "Bjorn");
        return "/query13/query13do";
    }

}
