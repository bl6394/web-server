package edu.wustl.elexicon.webserver.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Service
public class CsvWriter {

    private static final Logger log = LoggerFactory.getLogger(CsvWriter.class);

    public String writeCsv(List<Map<String, String>> items) throws IOException {
        StringWriter sw = new StringWriter();
        String [] headers = items.get(0).keySet().toArray(new String[items.get(0).size()]);
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL)
                .withHeader((String []) headers))) {
            items.forEach(row -> {
                try {
                    printer.printRecord(row.values());
                } catch (IOException e) {
                    log.error("error: ", e);
                }
            });
        }
        sw.flush();
        return sw.toString();
    }
}
