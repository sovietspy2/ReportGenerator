package hu.wortex.report.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
public class Marketplace {
    @Id
    @Column(unique=true)
    private Integer id;

    @JsonProperty("marketplace_name")
    private String marketplaceName;
}
