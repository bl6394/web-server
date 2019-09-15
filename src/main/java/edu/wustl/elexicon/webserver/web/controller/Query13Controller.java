package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.Query13LargeResponseProcessor;
import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import edu.wustl.elexicon.webserver.web.repository.ItemSQLHelper;
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
public class Query13Controller extends AbstractController{

    private final Logger log = LoggerFactory.getLogger(Query13Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;

    private final ItemRepository itemRepository;
    private final Query13LargeResponseProcessor query13LargeResponseProcessor;
    private final ItemSQLHelper itemSQLHelper;

    public Query13Controller(ItemRepository itemRepository, Query13LargeResponseProcessor query13LargeResponseProcessor) {
        this.itemRepository = itemRepository;
        this.query13LargeResponseProcessor = query13LargeResponseProcessor;
        this.itemSQLHelper = new ItemSQLHelper();
    }

    @PostMapping(value = "/query13/query13do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = initializeSession(log, session);
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        String sql = itemSQLHelper.getSQL(formData.get("field"), targetDb, formData.get("constraints"));
        String sizeSQL = itemSQLHelper.getSizeSQL(targetDb, formData.get("constraints"));
        log.info("Session Id: " + trxId + " SQL: " + sql);
        int querySize = itemRepository.getSize(sizeSQL);
        if (querySize == 0) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query13/query13.html");
            return "errorback";
        }
        if (querySize > maxHtmlResultSet || formData.get("dist").contains("email") ) {
            session.setAttribute("itemsSql", sql);
            session.setAttribute("targetDb", targetDb);
            return "query13/emailresponse";
        }
        List<Map<String, String>> query = itemRepository.get(sql);
        log.info("Session Id: " + trxId + " QuerySize: " + query.size() );
        model.addAttribute("dist", formData.get("dist"));
        model.addAttribute("scope", formData.get("scope"));
        model.addAttribute("constraints", formData.get("constraints"));
        model.addAttribute("field", formData.get("field"));
        model.addAttribute("items", query);
        model.addAttribute("itemCount", query.size());
        model.addAttribute("targetDb", formData.get("scope").contains("RESELP") ? "Restricted" : "Complete");
        addButtonsFlags(formData, model);
        return "query13/query13do";
    }

    @PostMapping(value = "/query13/query13domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Processing Email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query13/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        model.addAttribute("trxId", trxId);
        String sql =  (String) session.getAttribute("itemsSql");
        String targetDb = (String) session.getAttribute("targetDb");
        validation(sql, targetDb);
        query13LargeResponseProcessor.processLargeResult(trxId, emailAddress, sql, targetDb);
        log.info("Session Id: " + trxId + " Finished Query 13" );
        return "query13/query13doemail";
    }

    public void validation(String sql, String targetDb) {
        if (sql  == null){
            throw new RuntimeException("sql is null");
        }
        if (targetDb  == null){
            throw new RuntimeException("targetDb is null");
        }
    }


}
