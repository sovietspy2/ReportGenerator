package hu.wortex.report.helpers;

import hu.wortex.report.entities.*;
import hu.wortex.report.repositories.ListingRepository;
import hu.wortex.report.repositories.ListingStatusRepository;
import hu.wortex.report.repositories.LocationRepository;
import hu.wortex.report.repositories.MarketplaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityFastSaveHandler {

    private static final Logger log = LoggerFactory.getLogger(EntityFastSaveHandler.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingStatusRepository listingStatusRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @Transactional
    public void handleSave(List<ListingDTO> dtos) {


        /// get lists here

        ValidationUtility validationUtility = new ValidationUtility(null, null, null);

        listingRepository.saveAll(dtos.stream()
                .filter(validationUtility::isValidListing)
                .map(dto-> {
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
                    log.info(listing.getId());
                    entityManager.persist(listing);
                    return listing;
                }
        ).collect(Collectors.toList()));
        log.info("saving service done");
    }


}
