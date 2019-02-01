package hu.wortex.report.helpers;

import hu.wortex.report.config.Config;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FtpUploader {

    @Autowired
    private Config config;

    private FTPClient client = new FTPClient();

    private static final String REMOTE_FILE_NAME = "report.json";
    private static final Logger log = LoggerFactory.getLogger(FtpUploader.class);


    public void upload() {

        String username = config.getFtpUsername();
        String password = config.getFtpPassword();
        String host = config.getFtpHost();
        String path = config.getTmpFilePath();
        Integer port = Integer.valueOf(config.getFtpPort());

        try {

            client.connect(host, port);
            client.login(username, password);
            client.enterLocalPassiveMode();

            client.setFileType(FTP.BINARY_FILE_TYPE);

            File firstLocalFile = new File(path);

            String firstRemoteFile = REMOTE_FILE_NAME;
            try (InputStream inputStream = new FileInputStream(firstLocalFile)) {
                boolean done = client.storeFile(firstRemoteFile, inputStream);
                log.info("started uploading file");
                if (done) {
                    log.info("finished uploading file");
                }
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
