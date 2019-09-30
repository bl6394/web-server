package edu.wustl.elexicon.webserver.web.controller;

import edu.wustl.elexicon.webserver.service.CsvWriter;
import edu.wustl.elexicon.webserver.service.Mailer;
import edu.wustl.elexicon.webserver.service.Query19LargeResponseProcessor;
import edu.wustl.elexicon.webserver.web.domain.QueryDTO;
import edu.wustl.elexicon.webserver.web.repository.ArbitrarySQLHelper;
import edu.wustl.elexicon.webserver.web.repository.ArbitraryTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Query19Controller extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(Query19Controller.class);

    @Value("${maxquerysize}")
    private Integer maxHtmlResultSet;
    private final Query19LargeResponseProcessor query19LargeResponseProcessor;
    private final ArbitrarySQLHelper arbitrarySQLHelper;
    private final ArbitraryTableRepository arbitraryTableRepository;
    private final Mailer mailer;
    private final CsvWriter csvWriter;

    public Query19Controller(Query19LargeResponseProcessor query19LargeResponseProcessor, ArbitraryTableRepository arbitraryTableRepository, Mailer mailer, CsvWriter csvWriter) {
        this.arbitraryTableRepository = arbitraryTableRepository;
        this.query19LargeResponseProcessor = query19LargeResponseProcessor;
        this.csvWriter = csvWriter;
        this.mailer = mailer;
        this.arbitrarySQLHelper = new ArbitrarySQLHelper();
    }

    @PostMapping(value = "/query19/query19do", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String process(@RequestBody MultiValueMap<String, String> formData, HttpSession session) {
        initializeSession(log, session);
        List<String> fields = formData.get("field") == null ? new ArrayList<>() : formData.get("field");
        List<String> distubution = formData.get("dist");
        String targetDb = formData.get("scope").contains("RESELP") ? "item" : "itemplus";
        Boolean neiBtn = formData.get("field").contains("NEIBTN");
        session.setAttribute("FIELDS", fields);
        session.setAttribute("DISTRIBUTION", distubution);
        session.setAttribute("NEIBTN", neiBtn);
        session.setAttribute("TARGET_DB", targetDb);
        return formData.get("list").contains("tlist") ? "query19/query19list" : "query19/query19file";
    }

    @PostMapping(value = "/query19/query19filedo")
    public String processFile(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Process File" );
        if (file.isEmpty()) {
            model.addAttribute("errorMessage", "ERROR:  You must supply a file!");
            return "query19/query19file";
        }
        List<String> words = parseFile(log, file);
        return processArbitraryNonWords(model, session, trxId, words);
    }

    @PostMapping(value = "/query19/query19listdo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processList(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Process List" );
        String wordlist = formData.get("wordlist").get(0);
        List<String> words = parseString(wordlist);
        return processArbitraryNonWords(model, session, trxId, words);
    }

    private String processArbitraryNonWords(Model model, HttpSession session, String trxId, List<String> words) {
        log.info("Session Id: " + trxId + " WordsSize: " + words.size());
        List<String> fields = (List<String>) session.getAttribute("FIELDS");
        List<String> distubution = (List<String>) session.getAttribute("DISTRIBUTION");
        String targetDb = (String) session.getAttribute("TARGET_DB");
        String sql = arbitrarySQLHelper.getSQL(fields);
        String summarySql = arbitrarySQLHelper.getSummarySQL(fields);
        session.setAttribute("SQL", sql);
        session.setAttribute("SUMMARY_SQL", summarySql);
        session.setAttribute("WORDS", words);
        if (words.isEmpty()) {
            model.addAttribute("errorMessage", "You query generated no results!");
            model.addAttribute("errorBackLink", "/query19/query19.html");
            return "errorback";
        }
        if (words.size() > 200 || distubution.contains("email")) {
            return "query19/emailresponse";
        }
        QueryDTO queryDTO = arbitraryTableRepository.get(trxId, words, sql, summarySql,  targetDb);
        log.info("Session Id: " + trxId + " QuerySize: " + queryDTO.query.size());
        model.addAttribute("nwItems", queryDTO.query);
        model.addAttribute("nwItemCount", queryDTO.query.size());
        model.addAttribute("submissionCount", words.size());
        model.addAttribute("summary", queryDTO.summary);
        model.addAttribute("targetDb", targetDb.equals("item") ? "Restricted" : "Complete");
        Boolean neibtn = (Boolean) session.getAttribute("NEIBTN");
        addButtonFlags(model, neibtn);
        return "query19/query19final";
    }

    @PostMapping(value = "/query19/query19domore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processEmail(@RequestBody MultiValueMap<String, String> formData, Model model, HttpSession session) {
        String trxId = getTrxId(session);
        log.info("Session Id: " + trxId + " Processing email" );
        String emailAddress = formData.getFirst("address");
        log.info("Session Id: " + trxId + " Email: " + emailAddress );
        if (emailAddress.isEmpty()) {
            model.addAttribute("errorMessage", "You must supply an email address!");
            return "query19/emailresponse";
        }
        model.addAttribute("emailAddress", emailAddress);
        model.addAttribute("trxId", trxId);
        String targetDb = (String) session.getAttribute("TARGET_DB");
        List<String> words = (List<String>) session.getAttribute("WORDS");
        String sql = (String) session.getAttribute("SQL");
        String summarySql = (String) session.getAttribute("SUMMARY_SQL");
        query19LargeResponseProcessor.processLargeResult(trxId, emailAddress, words, sql, summarySql, targetDb);
        return "query19/query19doemail";
    }

    private void addButtonFlags(Model model, Boolean value ) {
        model.addAttribute("neiButtonFlag", value);
    }




}
