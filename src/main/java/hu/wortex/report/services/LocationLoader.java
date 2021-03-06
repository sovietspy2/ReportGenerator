package hu.wortex.report.services;

import hu.wortex.report.config.Config;
import hu.wortex.report.entities.Location;
import hu.wortex.report.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Order(2)
public class LocationLoader implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Config config;

    private static final Logger log = LoggerFactory.getLogger(LocationLoader.class);

    @Override
    public void run(String... args) {

        log.info("Location loader begin");

        ResponseEntity<List<Location>> response =
                restTemplate.exchange(config.getLocationApiUrl(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {
                        });
        List<Location> location = response.getBody();

        log.info("Fetched data from apiurl");

        locationRepository.saveAll(location);

        log.info("saving recors to LOCATION table is done");
    }
}
