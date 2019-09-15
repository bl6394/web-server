package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.repository.ItemRepository;
import edu.wustl.elexicon.webserver.web.repository.NeighborRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Query13LargeResponseProcessor {

    private final Logger log = LoggerFactory.getLogger(Query13LargeResponseProcessor.class);

    private final Mailer mailer;
    private final CsvWriter csvWriter;
    private final ItemRepository itemRepository;
    private final NeighborRepository neighborRepository;

    public Query13LargeResponseProcessor(ItemRepository itemRepository, NeighborRepository neighborRepository, Mailer mailer, CsvWriter csvWriter) {
        this.itemRepository = itemRepository;
        this.neighborRepository = neighborRepository;
        this.mailer = mailer;
        this.csvWriter = csvWriter;
    }

        @Async("threadPoolTaskExecutor")
    public void processLargeResult(String trxId, String emailAddress, String sql, String targetDb) {
        final List<Map<String, String>> items = itemRepository.get(sql);
        if (items != null) {
            try {
                Map<String, String> attachments = new HashMap<>();
                String itemsCsv = csvWriter.writeCsv(items);
                attachments.put("Items.csv", itemsCsv);
                List<Map<String, String>> allNeighbors = neighborRepository.getMany(convertItemsToWords(items), "neighbors", targetDb  );
                String neighborhoodCsv = csvWriter.writeCsv(allNeighbors);
                attachments.put("Neighborhood.csv", neighborhoodCsv);
                mailer.sendMessage(trxId, attachments, emailAddress);
                log.info("Session Id: " + trxId + " Finished  Large Response for Query 13" );
            } catch (IOException e) {
                log.error("error",  e);
            }
        }
    }

    private List<String> convertItemsToWords(List<Map<String, String>> items) {
        List<String> words = new ArrayList<>();
        for (Map<String,String> item: items){
            words.add(item.get("Word"));
        }
        return words;
    }
}
