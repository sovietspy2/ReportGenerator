package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

//import hu.wortex.report.helpers.DateDeserializer;

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

    @Column(precision=2)
    private Double listingPrice;

    private String currency;

    private Integer quantity;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="listing_status", referencedColumnName="id", nullable=false)
    private ListingStatus listingStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="marketplace", referencedColumnName="id", nullable=false)
    private Marketplace marketPlace;

    private Date uploadTime;

    private String ownerEmailAddress;

}
