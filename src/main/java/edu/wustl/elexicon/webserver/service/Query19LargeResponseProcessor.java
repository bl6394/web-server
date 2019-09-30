package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.domain.QueryDTO;
import edu.wustl.elexicon.webserver.web.repository.ArbitraryTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Query19LargeResponseProcessor {

    private final Logger log = LoggerFactory.getLogger(Query19LargeResponseProcessor.class);

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final ArbitraryTableRepository arbitraryTableRepository;

    public Query19LargeResponseProcessor(ArbitraryTableRepository arbitraryTableRepository, Mailer mailer, CsvWriter csvWriter) {
        this.arbitraryTableRepository = arbitraryTableRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

    @Async("threadPoolTaskExecutor")
    public void processLargeResult(String trxId, String emailAddress, List<String> words, String sql, String summarySql, String targetDb) {
        final QueryDTO queryDTO = arbitraryTableRepository.get(trxId, words , sql, summarySql, targetDb);
        if (queryDTO.query != null) {
            try {
                Map<String, String> attachments = new HashMap<>();
                String itemsCsv = csvWriter.writeCsv(queryDTO.query);
                attachments.put("ArbitraryNonWords.csv", itemsCsv);
                String neighborhoodCsv = csvWriter.writeCsv(queryDTO.neighborhood);
                attachments.put("Neighborhood.csv", neighborhoodCsv);
                String summaryCsv = csvWriter.writeCsv(queryDTO.summary);
                attachments.put("Summary.csv", summaryCsv);
                mailer.sendMessage(trxId, attachments, emailAddress);
                log.info("Session Id: " + trxId + " Finished  Large Response for Query 19" );
            } catch (IOException e) {
                log.error("error",  e);
            }
        }
    }

}
