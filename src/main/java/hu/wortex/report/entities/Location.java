package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @Id
    @Column(unique=true)
    private String id;

    @JsonProperty("manager_name")
    private String managerName;

    private String phone;

    @JsonProperty("address_primary")
    private String addressPrimary;

    @JsonProperty("address_secondary")
    private String addressSecondary;

    private String country;

    private String town;

    @JsonProperty("postal_code")
    private String postalCode;
}
