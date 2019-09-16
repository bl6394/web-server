package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.*;
import edu.wustl.elexicon.webserver.web.repository.LexicalDataSQLHelper;
import edu.wustl.elexicon.webserver.web.repository.NamingDataRepository;
import edu.wustl.elexicon.webserver.web.repository.NamingDataSQLHelper;
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

@Controller
public class Query18Controller extends AbstractController{

    private final Logger log = LoggerFactory.getLogger(Query18Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final Query18LargeResponseProcessor query18LargeResponseProcessor;
    private final NamingDataSQLHelper namingDataSQLHelper;
    private final NamingDataRepository namingDataRepository;

    public Query18Controller(NamingDataRepository namingDataRepository, Query18LargeResponseProcessor query18LargeResponseProcessor) {
        this.namingDataRepository = namingDataRepository;
        this.query18LargeResponseProcessor = query18LargeResponseProcessor;
        this.namingDataSQLHelper = new NamingDataSQLHelper();

    }

    @PostMapping(value = "/query18/query18do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = initializeSession(log, session);
        String sizeSQL = namingDataSQLHelper.getSizeSQL(formData.get("constraints"));
        String sql = namingDataSQLHelper.getSQL(formData.get("field"), formData.get("constraints"));
        log.info("Session Id: " + trxId + " SQL: " + sql);
        int querySize = namingDataRepository.getSize(sizeSQL);
        if ( querySize >= 100000){
            model.addAttribute("errorMessage", "You query generated " + querySize + " results!  The limit for an individual query is 100,000 results.  Please add constraints...");
            model.addAttribute("errorBackLink", "/query18/query18.html");
            return "errorback";
        }
        if (querySize == 0) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query18/query18.html");
            return "errorback";
        }
        if (querySize > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            session.setAttribute("namingDataSql", sql);
            return "query18/emailresponse";
        }
        List<Map<String, String>> query = namingDataRepository.get(sql);
        log.info("Session Id: " + trxId + " QuerySize: " + query.size() );
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("expData", query);
        model.addAttribute("expDataCount", query.size());
        addButtonsFlags(formData, model);
        return "query18/query18do";
    }

    @PostMapping(value = "/query18/query18domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        String sql =  (String) session.getAttribute("namingDataSql");
        log.info("Session Id: " + trxId + " Processing Email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query18/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        model.addAttribute("trxId", trxId);
        query18LargeResponseProcessor.processLargeResult(trxId, emailAddress, sql);
        return "query18/query18doemail";
    }


}
