package hu.wortex.report.helpers;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FtpUploader {

    private FTPClient client;

    private String username;
    private String password;
    private String host;
    private String path;
    private Integer port;

    private static final String REMOTE_FILE_NAME = "report.json";

    private static final Logger log = LoggerFactory.getLogger(FtpUploader.class);

    public FtpUploader(String username, String password, String host, String path, String port)  {
        this.username = username;
        this.password = password;
        this.port = Integer.valueOf(port);
        this.host = host;
        this.path = path;
        client = new FTPClient();
    }

    public void upload() {

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
            ex.printStackTrace();
        } finally {
            try {
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
