package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.repository.NonWordItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Query8LargeResponseProcessor {

    private final Logger log = LoggerFactory.getLogger(Query8LargeResponseProcessor.class);

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final NonWordItemRepository nonWordItemRepository;

    public Query8LargeResponseProcessor(NonWordItemRepository nonWordItemRepository, Mailer mailer, CsvWriter csvWriter) {
        this.nonWordItemRepository = nonWordItemRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @Async("threadPoolTaskExecutor")
    public void processLargeResult(String trxId, String emailAddress, String sql) {
        List<Map<String, String>> query = nonWordItemRepository.get(sql);
        if (query != null) {
            try {
                String csv = csvWriter.writeCsv(query);
                Map<String, String> attachments = new HashMap<>();
                attachments.put("NonWord.csv", csv);
                mailer.sendMessage(trxId, attachments, emailAddress);
                log.info("Session Id: " + trxId + " Finished Large Response for Query 8" );
            } catch (IOException e) {
                log.error("error", e);
            }
        }
    }
}
