package hu.wortex.report.helpers;

import hu.wortex.report.entities.Listing;
import hu.wortex.report.entities.ListingDTO;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class ValidationUtility {

    private static final String REGEX_PATTERN = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

    private List<String> locationIds;
    private List<Integer> marketplaceIds;
    private List<Integer> listingStatusIds;

    public boolean isValidListing(ListingDTO listing) {

        if (!listing.getId().matches(REGEX_PATTERN)) {
            return false;
        } else if (listing.getTitle() == null) {
            return false;
        } else if (listing.getDescription() == null) {
            return false;
        } else if (listing.getLocationId() == null || !locationIds.contains(listing.getLocationId())) { // meg kell nezni a DB-ben
            return false;
        } else if (listing.getListingPrice() == null || listing.getListingPrice() < 0) {// 2 tizedesjegy
            return false;
        } else if (listing.getCurrency() == null || listing.getCurrency().length() != 3) {// vagy nem pontosan 3 karakter
            return false;
        } else if (listing.getQuantity() == null || listing.getQuantity() < 0) {
            return false;
        } else if (listing.getListingStatusId() == null || !listingStatusIds.contains(listing.getListingStatusId())) { // meg kell nezni db
            return false;
        } else if (listing.getMarketPlaceId() == null || !marketplaceIds.contains(listing.getMarketPlaceId())) { // meg kell nezni db
            return false;
        } else if (listing.getOwnerEmailAddress() == null || !isValidEmail(listing.getOwnerEmailAddress())) {
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        email = email.trim();
        EmailValidator eValidator = EmailValidator.getInstance();
        return eValidator.isValid(email);
    }

    public ValidationUtility(List<String> locationIds,
                             List<Integer> marketplaceIds,
                             List<Integer> listingStatusIds) {

        this.locationIds = locationIds;
        this.listingStatusIds = listingStatusIds;
        this.marketplaceIds = marketplaceIds;

    }
}
