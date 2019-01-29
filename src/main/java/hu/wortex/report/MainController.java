package hu.wortex.report;

import hu.wortex.report.entities.Listing;
import hu.wortex.report.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired // This means to get the bean called userRepository
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path="/syncListings") // Map ONLY GET Requests
    public @ResponseBody String syncListings () {

        ResponseEntity<List<Listing>> rateResponse =
                restTemplate.exchange("http://5c50abb1ee97f60014048115.mockapi.io/boki/BOOK",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Listing>>() {
                        });
        List<Listing> rates = rateResponse.getBody();

        // VALIDACIO ES MENTES

        return "processed";
    }

    @GetMapping(path="/syncLocation")
    public @ResponseBody String syncLocation() {

        return "Ok";
    }

    @GetMapping(path="/syncListingStatus")
    public @ResponseBody String syncListingStatus() {

        return "Ok";
    }

    @GetMapping(path="/syncMarketplace")
    public @ResponseBody String syncMarketplace() {

        return "Ok";
    }
}