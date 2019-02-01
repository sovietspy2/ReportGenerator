package hu.wortex.report.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties
public class Config {
    String listingStatusApiUrl;
    String listingApiUrl;
    String locationApiUrl;
    String marketplaceApiUrl;
    String csvFilePath;
    String tmpFilePath;
    String ftpUsername;
    String ftpPassword;
    String ftpHost;
    String ftpPort;
}
