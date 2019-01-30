//package hu.wortex.report.services;
//
//import hu.wortex.report.entities.Listing;
//import hu.wortex.report.repositories.ListingRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Component
//public class StreamReader {
//
//    private static final Logger log = LoggerFactory.getLogger(StreamReader.class);
//
//    @Transactional(readOnly = true)
//    public List<Listing> readStream(ListingRepository repository) {
//
//        try (Stream<Listing> listingStream = repository.streamAll()) {
//            return listingStream.filter(listing-> {
////                log.info(map.getId());
////                map.setTitle("XXX");
////                log.info(map.getTitle());
//                //return isValidListing(listing);
//            }).collect(Collectors.toList());
//        }
//    }
//
//
//
//}
