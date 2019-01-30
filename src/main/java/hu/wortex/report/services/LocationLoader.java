package hu.wortex.report.services;

import hu.wortex.report.entities.ListingStatus;
import hu.wortex.report.entities.Location;
import hu.wortex.report.repositories.ListingRepository;
import hu.wortex.report.repositories.LocationRepository;
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


    @Override
    public void run(String... args) throws Exception {

        locationRepository.deleteAll();

        ResponseEntity<List<Location>> response  =
                restTemplate.exchange("https://my.api.mockaroo.com/location?key=63304c70",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {
                        });
        List<Location> location = response.getBody();

        locationRepository.saveAll(location);
    }
}
