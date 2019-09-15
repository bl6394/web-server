package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.repository.NamingDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Query18LargeResponseProcessor {

    private final Logger log = LoggerFactory.getLogger(Query18LargeResponseProcessor.class);

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final NamingDataRepository namingDataRepository;

    public Query18LargeResponseProcessor(NamingDataRepository namingDataRepository, Mailer mailer, CsvWriter csvWriter) {
        this.namingDataRepository = namingDataRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @Async("threadPoolTaskExecutor")
    public void processLargeResult(String trxId, String emailAddress, String sql) {
        List<Map<String, String>> query = namingDataRepository.get(sql);
        if (query != null) {
            try {
                Map<String, String> attachments = new HashMap<>();
                String csv = csvWriter.writeCsv(query);
                attachments.put("NamingData.csv", csv);
                mailer.sendMessage(trxId, attachments, emailAddress);
                log.info("Session Id: " + trxId + " Finished Large Response for Query 18" );
            } catch (IOException e) {
                log.error("error: ", e);
            }
        }
    }
}
