package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.service.Query8LargeResponseProcessor;
import edu.wustl.elexicon.webserver.web.repository.NonWordItemRepository;
import edu.wustl.elexicon.webserver.web.repository.NonWordSQLHelper;
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
public class Query8Controller extends AbstractController{

    private final Logger log = LoggerFactory.getLogger(Query8Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final NonWordItemRepository nonWordItemRepository;
    private final NonWordSQLHelper nonWordSQLHelper = new NonWordSQLHelper();
    private final Query8LargeResponseProcessor query8LargeResponseProcessor;

    public Query8Controller(NonWordItemRepository nonWordItemRepository, Query8LargeResponseProcessor query8LargeResponseProcessor) {
        this.nonWordItemRepository = nonWordItemRepository;
        this.query8LargeResponseProcessor =  query8LargeResponseProcessor;
    }

    @PostMapping(value = "/query8/query8do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = UUID.randomUUID().toString();
        session.setAttribute("TRX_ID", trxId);
        log.info("Session Id: " + trxId + " Starting" );
        String sql = nonWordSQLHelper.getSQL(formData.get("field"), formData.get("constraints"));
        String sizeSQL = nonWordSQLHelper.getSizeSQL(formData.get("constraints"));
        log.info("Session Id: " + trxId + " SQL: " + sql);
        int querySize = nonWordItemRepository.getSize(sizeSQL);
        if (querySize == 0) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query8/query8.html");
            return "errorback";
        }
        if (querySize > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            session.setAttribute("nonWordSql", sql);
            return "query8/emailresponse";
        }
        List<Map<String, String>> query = nonWordItemRepository.get(sql);
        log.info("Session Id: " + trxId + " QuerySize: " +  query.size() );
        session.setAttribute("nwItemData", query);
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("nwItem", query);
        model.addAttribute("nwItemCount", query.size());
        addButtonFlags(formData, model);
        return "query8/query8do";
    }

    @PostMapping(value = "/query8/query8domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Process Email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query8/emailresponse";
        }
        final String sql = (String) session.getAttribute("nonWordSql");
        model.addAttribute("emailAddress", emailAddress);
        model.addAttribute("trxId", trxId);
        query8LargeResponseProcessor.processLargeResult(trxId, emailAddress, sql);
        return "query8/query8doemail";
    }

    private void addButtonFlags(@RequestBody MultiValueMap<String, String> formData, Model model) {
        if (formData.get("field") == null) {
            return;
        }
        model.addAttribute("orthoButtonFlag", formData.get("field").contains("OrthoBTN"));
    }



}
