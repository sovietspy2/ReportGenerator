package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import java.util.Date;

@Data
public class ListingDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("listing_price")
    private Long listingPrice;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("listing_status")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer listingStatusId;


    @JsonProperty("marketplace")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer marketPlaceId;

    @JsonFormat(pattern = "MM/dd/yyyy")
    //@JsonDeserialize(using= DateDeserializer.class)
    @JsonProperty("upload_time")
    private Date uploadTime;

    @JsonProperty("owner_email_address")
    private String ownerEmailAddress;
}
