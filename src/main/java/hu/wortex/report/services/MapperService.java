package hu.wortex.report.services;

import hu.wortex.report.entities.*;
import hu.wortex.report.helpers.CsvGenerator;
import hu.wortex.report.helpers.ValidationUtility;
import hu.wortex.report.repositories.ListingStatusRepository;
import hu.wortex.report.repositories.LocationRepository;
import hu.wortex.report.repositories.MarketplaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class MapperService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ListingStatusRepository listingStatusRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @Autowired
    private CsvGenerator csvGenerator;

    private static final Logger log = LoggerFactory.getLogger(MapperService.class);

    /**
     * saves valid Listings to the database
     *
     * @param dtos
     */
    @Transactional
    public void handleSave(List<ListingDTO> dtos) throws IOException {

        /// get lists here
        List<String> locationIds = getLocationIds();
        log.info("location id list for validation received");
        List<Integer> marketplaceIds = getMarketplaceIds();
        log.info("marketplace id list for validation received");
        List<Integer> listingStatusIds = getListingStatusIds();
        log.info("listing status id list for validation received");

        Map<Integer, String> marketplacesMap = createIdNameMarketplaceMap();

        ValidationUtility validationUtility = new ValidationUtility(locationIds, marketplaceIds, listingStatusIds, marketplacesMap);

        dtos.stream()
                .filter(validationUtility::isValidListing)
                .forEach(dto -> {
                            Marketplace marketplace = entityManager.getReference(Marketplace.class, dto.getMarketPlaceId());
                            Location location = entityManager.getReference(Location.class, dto.getLocationId());
                            ListingStatus listingStatus = entityManager.getReference(ListingStatus.class, dto.getListingStatusId());

                            Listing listing = new Listing();
                            listing.setId(dto.getId());
                            listing.setCurrency(dto.getCurrency());
                            listing.setDescription(dto.getDescription());

                            listing.setListingPrice(dto.getListingPrice());
                            listing.setOwnerEmailAddress(dto.getOwnerEmailAddress());
                            listing.setQuantity(dto.getQuantity());
                            listing.setTitle(dto.getTitle());
                            listing.setUploadTime(dto.getUploadTime());

                            listing.setListingStatus(listingStatus);

                            listing.setMarketPlace(marketplace);

                            listing.setLocation(location);
                            log.debug(listing.getId());
                            entityManager.persist(listing);
                        }
                );
        log.info("Listins saved to database");

        log.info("generating CSV file");
        csvGenerator.generateCsv(validationUtility.getCsvLines());
        log.info("CSV file generating is done");

    }

    private List<Integer> getListingStatusIds() {
        return StreamSupport.stream(listingStatusRepository.findAll().spliterator(), true)
                .map(ListingStatus::getId)
                .collect(Collectors.toList());
    }

    private List<Integer> getMarketplaceIds() {
        return StreamSupport.stream(marketplaceRepository.findAll().spliterator(), true)
                .map(Marketplace::getId)
                .collect(Collectors.toList());
    }

    private List<String> getLocationIds() {
        return StreamSupport.stream(locationRepository.findAll().spliterator(), true)
                .map(Location::getId)
                .collect(Collectors.toList());
    }

    private Map<Integer, String> createIdNameMarketplaceMap() {
        return StreamSupport.stream(marketplaceRepository.findAll().spliterator(), true)
                .collect(Collectors.toMap(Marketplace::getId, Marketplace::getMarketplaceName));
    }


}
