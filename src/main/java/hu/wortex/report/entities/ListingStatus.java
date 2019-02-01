package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "listing_status")
public class ListingStatus {
    @Id
    @Column(unique = true)
    private Integer id;

    @JsonProperty("status_name")
    @Column(name = "status_name")
    private String statusName;
}
