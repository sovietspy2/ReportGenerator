package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import hu.wortex.report.helpers.DateDeserializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
public class Listing {

    @Id
    private String id;

    private String title;

    private String description;

    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("listing_price")
    private Integer listingPrice;

    private String currency;

    private Integer quantity;

    @JsonProperty("listing_status")
    private Integer listingStatus;


    @JsonProperty("marketplace")
    private Integer marketPlace;

    @JsonFormat(pattern = "MM/dd/yyyy")
    //@JsonDeserialize(using= DateDeserializer.class)
    @JsonProperty("upload_time")
    private Date uploadTime;

    @JsonProperty("owner_email_address")
    private String ownerEmailAddress;


}
