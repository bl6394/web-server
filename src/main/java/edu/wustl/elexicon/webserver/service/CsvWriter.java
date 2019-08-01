package edu.wustl.elexicon.webserver.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Service
public class CsvWriter {

    public String writeCsv(String name, List<Map<String, String>> items) throws IOException {
        StringWriter sw = new StringWriter();
        String [] headers = items.get(0).keySet().toArray(new String[items.get(0).size()]);
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withQuoteMode(QuoteMode.ALL)
                .withHeader((String []) headers))) {
            items.forEach(row -> {
                try {
                    printer.printRecord(row.values());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        sw.flush();
        return sw.toString();
    }
}
