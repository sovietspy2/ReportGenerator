package hu.wortex.report.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CsvGenerator {

    private String csvSaveLocation;

    private static final String FILE_NAME = "importLog.csv";

    public CsvGenerator(String csvSaveLocation) {
        this.csvSaveLocation = csvSaveLocation;
    }

    private static final String[] HEADERS = {"ListingId","MarketplaceName","InvalidField"};

    private static final Logger log = LoggerFactory.getLogger(CsvGenerator.class);

    public String generateCsv(List<List<String>> data) throws IOException {

        SimpleDateFormat fileFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        String currentTime = fileFormatter.format(new Date(System.currentTimeMillis()));

        String fileName = csvSaveLocation+currentTime+FILE_NAME;

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

        log.info("created csv file: "+fileName);

        return fileName;
    }

}
