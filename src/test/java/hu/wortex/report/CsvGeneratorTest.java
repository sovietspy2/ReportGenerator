package hu.wortex.report;

import hu.wortex.report.helpers.CsvGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class CsvGeneratorTest {

    @Test
    public void csvTest() throws IOException {
        CsvGenerator csv = new CsvGenerator("/csv/");
        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("testid", "ebay", "fieldname"));
        data.add(Arrays.asList("testId2", "amazon", "fieldname2"));

        String fileName = csv.generateCsv(data);

        File file = new File(fileName);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void csvTestMissingMarketplaceName() throws IOException {
        CsvGenerator csv = new CsvGenerator("/csv/");
        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("testid", null, "fieldname"));
        data.add(Arrays.asList("testId2", "amazon", "fieldname2"));

        String fileName = csv.generateCsv(data);

        File file = new File(fileName);
        Assert.assertTrue(file.exists());
    }

}
