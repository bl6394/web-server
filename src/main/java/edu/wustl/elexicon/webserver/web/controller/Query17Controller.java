package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.Query17LargeResponseProcessor;
import edu.wustl.elexicon.webserver.web.repository.LexicalDataRepository;
import edu.wustl.elexicon.webserver.web.repository.LexicalDataSQLHelper;
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
import java.util.List;
import java.util.Map;

@Controller
public class Query17Controller extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(Query17Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final LexicalDataRepository lexicalDataRepository;
    private final Query17LargeResponseProcessor query17LargeResponseProcessor;
    private final LexicalDataSQLHelper lexicalDataSQLHelper;

    public Query17Controller(LexicalDataRepository lexicalDataRepository, Query17LargeResponseProcessor query17LargeResponseProcessor) {
        this.lexicalDataRepository = lexicalDataRepository;
        this.query17LargeResponseProcessor = query17LargeResponseProcessor;
        this.lexicalDataSQLHelper = new LexicalDataSQLHelper();
    }

    @PostMapping(value = "/query17/query17do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = initializeSession(log, session);
        String sizeSQL = lexicalDataSQLHelper.getSizeSQL(formData.get("constraints"));
        String sql = lexicalDataSQLHelper.getSQL(formData.get("field"), formData.get("constraints"));
        int querySize = lexicalDataRepository.getSize(sizeSQL);
        log.info("Session Id: " + trxId + " SQL:" + sql);
        if ( querySize >= 100000){
            log.info("Session Id: " + trxId + " Result set > 100000:" + querySize);
            model.addAttribute("errorMessage", "You query generated " + querySize + " results!  Please add constraints...");
            model.addAttribute("errorBackLink", "/query17/query17.html");
            return "errorback";
        }
        if (querySize == 0) {
            log.info("Session Id: " + trxId + " empty query.");
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query17/query17.html");
            return "errorback";
        }
        if (querySize > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            log.info("Session Id: " + trxId + " large query:" + querySize);
            session.setAttribute("lexicalDataSql", sql);
            return "query17/emailresponse";
        }
        List<Map<String, String>> query = lexicalDataRepository.get(sql);
        log.info("Session Id: " + trxId + " QuerySize: " + query.size() );
        session.setAttribute("expData", query);
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("expData", query);
        model.addAttribute("expDataCount", query.size());
        addButtonsFlags(formData, model);
        return "query17/query17do";
    }

    @PostMapping(value = "/query17/query17domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        String sql =  (String) session.getAttribute("lexicalDataSql");
        log.info("Session Id: " + trxId + " Processing email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query17/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        model.addAttribute("trxId", trxId);
        query17LargeResponseProcessor.processLargeResult(trxId, emailAddress, sql);
        return "query17/query17doemail";
    }



}
