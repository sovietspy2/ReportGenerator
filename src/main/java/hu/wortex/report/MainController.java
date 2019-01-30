//package hu.wortex.report;
//
//import hu.wortex.report.entities.Listing;
//import hu.wortex.report.entities.Marketplace;
//import hu.wortex.report.repositories.ListingRepository;
//import hu.wortex.report.repositories.ListingStatusRepository;
//import hu.wortex.report.repositories.LocationRepository;
//import hu.wortex.report.repositories.MarketplaceRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(path="/")
//public class MainController {
//    @Autowired
//    private ListingRepository listingRepository;
//
//    @Autowired
//    private MarketplaceRepository marketplaceRepository;
//
//    @Autowired
//    private ListingStatusRepository listingStatusRepository;
//
//    @Autowired
//    private LocationRepository locationRepository;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final Logger log = LoggerFactory.getLogger(MainController.class);
//
//    @GetMapping(path="/syncListings") // Map ONLY GET Requests
//    public @ResponseBody String syncListings () {
//
//        ResponseEntity<List<Listing>> rateResponse =
//                restTemplate.exchange("https://my.api.mockaroo.com/listing?key=63304c70",
//                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Listing>>() {
//                        });
//        List<Listing> rates = rateResponse.getBody();
//
//        // VALIDACIO ES MENTES
//
//        return "processed";
//    }
//
//    @GetMapping(path="/process")
//    public @ResponseBody String syncLocation() {
//
//        Listing listing = listingRepository.findById("1").get();
//        log.info(String.valueOf(listing.getListingStatus()));
//
//
//        return "Ok";
//    }
//
//    @GetMapping(path="/syncListingStatus")
//    public @ResponseBody String syncListingStatus() {
//
//        return "Ok";
//    }
//
//    @GetMapping(path="/syncMarketplace")
//    public @ResponseBody String syncMarketplace() {
//
//        return "Ok";
//    }
//}