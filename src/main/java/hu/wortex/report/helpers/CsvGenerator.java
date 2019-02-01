package hu.wortex.report.helpers;

import hu.wortex.report.config.Config;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CsvGenerator {

    private Config config;

    public CsvGenerator(Config config) {
        this.config = config;
    }

    private static final String[] HEADERS = {"ListingId", "MarketplaceName", "InvalidField"};

    private static final Logger log = LoggerFactory.getLogger(CsvGenerator.class);

    public String generateCsv(List<List<String>> data) throws IOException {

        final String csvSaveLocation = config.getCsvFilePath();

        SimpleDateFormat fileFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        String currentTime = fileFormatter.format(new Date(System.currentTimeMillis()));

        String fileName = csvSaveLocation + currentTime + config.getImportFileName();

        FileWriter out = new FileWriter(fileName);

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            data.forEach(row -> {
                try {
                    printer.printRecord(row);
                } catch (IOException e) {
                    log.error("failed to write csv", e.getMessage(), e);
                }
            });
        }

        log.info("created csv file: " + fileName);

        return fileName;
    }

}
