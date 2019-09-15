package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.repository.LexicalDataRepository;
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
public class Query17LargeResponseProcessor {

    private final Logger log = LoggerFactory.getLogger(Query17LargeResponseProcessor.class);

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final LexicalDataRepository lexicalDataRepository;

    public Query17LargeResponseProcessor(LexicalDataRepository lexicalDataRepository, Mailer mailer, CsvWriter csvWriter) {
        this.lexicalDataRepository = lexicalDataRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @Async("threadPoolTaskExecutor")
    public void processLargeResult(String trxId, String emailAddress, String sql) {
        List<Map<String, String>> query = lexicalDataRepository.get(sql);
        if (query != null) {
            try {
                Map<String, String> attachments = new HashMap<>();
                String csv = csvWriter.writeCsv(query);
                attachments.put("LexicalData.csv", csv);
                mailer.sendMessage(trxId, attachments, emailAddress);
                log.info("Session Id: " + trxId + " Finished Large Response for Query 17" );
            } catch (IOException e) {
                log.error("error: ", e);
            }
        }
    }
}
