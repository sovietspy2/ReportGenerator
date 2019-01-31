package hu.wortex.report.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.wortex.report.entities.MainReportDTO;
import hu.wortex.report.entities.MonthlyReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Order(1)
public class ReportMaker implements CommandLineRunner  {

    @Autowired
    private ReportCreator reportCreator;

    @Override
    public void run(String... args) throws Exception {

        MainReportDTO mainReportDTO = reportCreator.getReport();

        ObjectMapper mapper = new ObjectMapper();

        try {

            mapper.writeValue(new File("/csv/test.json"), mainReportDTO );

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(1);
    }
}
