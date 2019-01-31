package hu.wortex.report.entities;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public abstract class ReportBaseDTO {
    private Integer totalEbayListingCount;
    private Double totalEbayListingPrice;
    private Double averageEbayListingPrice;
    private Integer totalAmazonListingCount;
    private Double totalAmazonListingPrice;
    private Double averageAmazonListingPrice;
    private String bestListerEmailAddress;
}
