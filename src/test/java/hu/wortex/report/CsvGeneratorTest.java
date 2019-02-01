package hu.wortex.report;

import hu.wortex.report.config.Config;
import hu.wortex.report.helpers.CsvGenerator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class CsvGeneratorTest {

    private static final String TEST_FILE_PATH = "./";

    @Test
    public void csvContentAndExistenceTest() throws IOException {

        Config mockConfig = Mockito.mock(Config.class);
        mockConfig.setCsvFilePath("");

        CsvGenerator csvGenerator = new CsvGenerator(mockConfig);


        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("testid", "ebay", "fieldname"));
        data.add(Arrays.asList("testId2", "amazon", "fieldname2"));

        String fileName = csvGenerator.generateCsv(data);

        File file = new File(fileName);
        Assert.assertTrue(file.exists());

        try (
                Reader reader = Files.newBufferedReader(Paths.get(TEST_FILE_PATH+fileName));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)
        ) {
            CSVRecord secondRow = new ArrayList<>(csvParser.getRecords()).get(2);
            Assert.assertTrue(secondRow.get(1).equals("amazon"));
        }

        file.delete();
    }


    @Test
    public void csvTestMissingMarketplaceName() throws IOException {

        Config mockConfig = Mockito.mock(Config.class);
        mockConfig.setCsvFilePath("");

        CsvGenerator csvGenerator = new CsvGenerator(mockConfig);

        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("testid", null, "fieldname"));
        data.add(Arrays.asList("testId2", "amazon", "fieldname2"));

        String fileName = csvGenerator.generateCsv(data);

        File file = new File(fileName);
        Assert.assertTrue(file.exists());

        try (
                Reader reader = Files.newBufferedReader(Paths.get(TEST_FILE_PATH+fileName));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)
        ) {
            CSVRecord secondRow = new ArrayList<>(csvParser.getRecords()).get(2);
            Assert.assertTrue(secondRow.get(1).equals("amazon"));
        }

        file.delete();
    }

}
