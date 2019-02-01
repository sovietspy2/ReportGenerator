package hu.wortex.report.services;

import hu.wortex.report.config.Config;
import hu.wortex.report.entities.Listing;
import hu.wortex.report.entities.ListingStatus;
import hu.wortex.report.repositories.ListingStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Order(1)
public class ListingStatusLoader implements CommandLineRunner {

    @Autowired
    private Config config;

    @Autowired
    private ListingStatusRepository listingStatusRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(ListingStatusLoader.class);

    @Override
    public void run(String... args) {

        listingStatusRepository.deleteAll();

        log.info("deleted all records from LISTING_STATUS table");

        ResponseEntity<List<ListingStatus>> response =
                    restTemplate.exchange(config.getListingStatusApiUrl(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ListingStatus>>() {
                        });
        List<ListingStatus> status = response.getBody();

        listingStatusRepository.saveAll(status);

        log.info("saving to LISTING_STATUS table is done");
    }
}
