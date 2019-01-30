package hu.wortex.report.services;

import hu.wortex.report.entities.ListingDTO;
import hu.wortex.report.helpers.EntityFastSaveHandler;
import hu.wortex.report.repositories.ListingRepository;
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

//@Component
//@Order(4)
public class ListingLoader implements CommandLineRunner {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EntityFastSaveHandler entityFastSaveHandler;

    private static final Logger log = LoggerFactory.getLogger(ListingLoader.class);


    @Override
    public void run(String... args) throws Exception {

        log.info("Listing loader begin");

        listingRepository.deleteAll();

        log.info("deleted records from listing table");

        ResponseEntity<List<ListingDTO>> listingResponse =
                restTemplate.exchange("https://my.api.mockaroo.com/listing?key=63304c70",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ListingDTO>>() {
                        });
        List<ListingDTO> listings = listingResponse.getBody();

        log.info("saving listings started start ---");
        entityFastSaveHandler.handleSave(listings);
        log.info("saving listings done ---");

        System.exit(1);

    }
}
