package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class Query14Controller {

    private ItemRepository itemRepository;

    public Query14Controller(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @PostMapping (value = "/query14/query14do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        System.out.println(formData);
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
//        List<Map<String, String>> query = itemRepository.get(formData.get("field"), targetDb, formData.get("constraints"));
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
//        model.addAttribute("items", query);
//        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", formData.get("scope").contains("RESELP") ? "Restricted" : "Complete" );
        addButtonFlags(formData, model);
        return "query14do";
    }

    private void addButtonFlags(@RequestBody MultiValueMap<String, String> formData, Model model) {
        if (formData.get("field") == null) {
            return;
        }
        model.addAttribute("orthoButtonFlag", formData.get("field").contains("OrthoBTN"));
        model.addAttribute("phonoButtonFlag", formData.get("field").contains("PhonoBTN"));
        model.addAttribute("phonoHButtonFlag", formData.get("field").contains("PhonoHBTN"));
        model.addAttribute("ogButtonFlag", formData.get("field").contains("OGBTN"));
        model.addAttribute("oghButtonFlag", formData.get("field").contains("OGHBTN"));
    }

}
