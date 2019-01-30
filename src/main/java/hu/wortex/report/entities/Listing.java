package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import hu.wortex.report.helpers.DateDeserializer;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="listing")
public class Listing {

    @Id
    @Column(unique=true)
    private String id;

    private String title;

    private String description;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="location_id", referencedColumnName="id", nullable=false)
    private Location location;

    //@JsonProperty("location_id")
    //private transient String locationId;

    @JsonProperty("listing_price")
    private Integer listingPrice;

    private String currency;

    private Integer quantity;

    @ManyToOne
    @JsonProperty("listing_status")
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="listing_status", referencedColumnName="id", nullable=false)
    private ListingStatus listingStatus;


    @ManyToOne(fetch=FetchType.LAZY)
    @JsonProperty("marketplace")
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="marketplace", referencedColumnName="id", nullable=false)
    private Marketplace marketPlace;

    @JsonFormat(pattern = "MM/dd/yyyy")
    //@JsonDeserialize(using= DateDeserializer.class)
    @JsonProperty("upload_time")
    private Date uploadTime;

    @JsonProperty("owner_email_address")
    private String ownerEmailAddress;

}
