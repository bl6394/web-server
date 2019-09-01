package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.web.repository.NamingDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class Query18Controller {

    private final Logger log = LoggerFactory.getLogger(Query18Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final NamingDataRepository namingDataRepository;

    public Query18Controller(NamingDataRepository namingDataRepository, Mailer mailer, CsvWriter csvWriter) {
        this.namingDataRepository = namingDataRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @PostMapping(value = "/query18/query18do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        List<Map<String, String>> query = namingDataRepository.get(formData.get("field"), formData.get("constraints"));
        session.setAttribute("expData1", query);
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("expData", query);
        model.addAttribute("expDataCount", query.size());
        addButtonFlags(formData, model);
        if (query.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query18/query18.html");
            return "errorback";
        }
        if (query.size() > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            return "query18/emailresponse";
        }
        return "query18/query18do";
    }

    @PostMapping(value = "/query18/query18domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String emailAddress = formData.getFirst("address");
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query18/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        final List<Map<String, String>> expData = (List<Map<String, String>>) session.getAttribute("expData1");
        if (expData != null) {
            String uuid = UUID.randomUUID().toString();
            model.addAttribute("trxId", uuid);
            try {
                String csv = csvWriter.writeCsv(expData);
                Map<String, String> attachments = new HashMap<>();
                attachments.put("NamingData.csv", csv);
                mailer.sendMessage(uuid, attachments, emailAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "query18/query18doemail";
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
