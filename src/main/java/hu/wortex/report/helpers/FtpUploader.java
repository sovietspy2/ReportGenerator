package hu.wortex.report.helpers;

import hu.wortex.report.config.Config;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FtpUploader {

    @Autowired
    private Config config;

    private FTPClient client = new FTPClient();

    private static final Logger log = LoggerFactory.getLogger(FtpUploader.class);

    public void upload() {

        String username = config.getFtpUsername();
        String password = config.getFtpPassword();
        String host = config.getFtpHost();
        String path = config.getTmpFilePath();
        Integer port = Integer.valueOf(config.getFtpPort());
        String remoteFile = config.getReportName();

        try {

            client.connect(host, port);
            client.login(username, password);
            client.enterLocalPassiveMode();

            client.setFileType(FTP.BINARY_FILE_TYPE);

            File localFile = new File(path);

            try (InputStream inputStream = new FileInputStream(localFile)) {
                client.storeFile(remoteFile, inputStream);
                log.info("started uploading file");
            }

        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
