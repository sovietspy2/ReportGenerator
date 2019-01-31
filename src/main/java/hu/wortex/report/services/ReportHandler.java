package hu.wortex.report.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.wortex.report.entities.MainReportDTO;
import hu.wortex.report.helpers.FtpUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Order(5)
public class ReportHandler implements CommandLineRunner  {

    @Autowired
    private ReportCreator reportCreator;

    @Autowired
    private Environment env;

    private static final Logger log = LoggerFactory.getLogger(ReportHandler.class);

    @Override
    public void run(String... args) {

        MainReportDTO mainReportDTO = reportCreator.getReport();

        log.info(" reportDTO generated");

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(env.getProperty("tmp.file.path")), mainReportDTO );
            log.info(" temp report.json saved to drive");
        } catch (IOException e) {
            e.printStackTrace();
        }

        FtpUploader ftpUploader = new FtpUploader(
                env.getProperty("ftp.username"),
                env.getProperty("ftp.password"),
                env.getProperty("ftp.host"),
                env.getProperty("tmp.file.path"),
                env.getProperty("ftp.port")
        );

        ftpUploader.upload();
        log.info("report.json uploaded to FTP server");
    }
}
