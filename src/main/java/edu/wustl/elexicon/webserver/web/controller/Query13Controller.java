package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
public class Query13Controller {

    private final Logger log = LoggerFactory.getLogger(Query13Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final Mailer mailer;
    private ItemRepository itemRepository;

    public Query13Controller(ItemRepository itemRepository, Mailer mailer) {
        this.itemRepository = itemRepository;
        this.mailer = mailer;
    }

    @PostMapping(value = "/query13/query13do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model) {
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        List<Map<String, String>> query = itemRepository.get(formData.get("field"), targetDb, formData.get("constraints"));
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", formData.get("scope").contains("RESELP") ? "Restricted" : "Complete");
        addButtonFlags(formData, model);
        if (query.size() > maxHtmlResultSet) {
            return "query13/emailresponse";
        }
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query13/query13.html");
            return "errorback";
        }
        return "query13/query13do";
    }

    @PostMapping(value = "/query13/query13domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model) {
        String emailAddress = formData.getFirst("address");
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query13/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        mailer.sendMessage(null, emailAddress);
        return "query13/query13doemail";
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
