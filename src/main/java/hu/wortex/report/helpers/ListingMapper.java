package hu.wortex.report.helpers;

import hu.wortex.report.entities.*;
import hu.wortex.report.repositories.ListingStatusRepository;
import hu.wortex.report.repositories.LocationRepository;
import hu.wortex.report.repositories.MarketplaceRepository;
import hu.wortex.report.services.ListingLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingMapper {

    @Autowired
    private ListingStatusRepository listingStatusRepository;

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @Autowired
    private LocationRepository locationRepository;

    private static final Logger log = LoggerFactory.getLogger(ListingMapper.class);


    public Listing mapToEntity(ListingDTO dto) {


        log.info("started loading lists");

        List<Marketplace> mks = (List<Marketplace>) marketplaceRepository.findAll();
        List<Location> lks = (List<Location>) locationRepository.findAll();
        List<ListingStatus> lts = (List<ListingStatus>) listingStatusRepository.findAll();

        log.info("list loading done");



        Listing listing = new Listing();

        listing.setId(dto.getId());
        listing.setCurrency(dto.getCurrency());
        listing.setDescription(dto.getDescription());
        listing.setListingPrice(dto.getListingPrice());
        listing.setOwnerEmailAddress(dto.getOwnerEmailAddress());
        listing.setQuantity(dto.getQuantity());
        listing.setTitle(dto.getTitle());
        listing.setUploadTime(dto.getUploadTime());

        //listing.setListingStatus(lks.stream().filter(a->a.getId().equals(dto.getListingStatusId())).findFirst());

        listing.setMarketPlace(findMarketplace(dto.getMarketPlaceId()));

        listing.setLocation(findLocation(dto.getLocationId()));

        return listing;
    }

    private ListingStatus findListingStatus(Integer id) {
        return listingStatusRepository.findById(id).orElse(null);
    }

    private Marketplace findMarketplace(Integer id) {
        return marketplaceRepository.findById(id).orElse(null);
    }

    private Location findLocation(String id) {
        return locationRepository.findById(id).orElse(null);
    }

}
