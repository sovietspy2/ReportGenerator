package hu.wortex.report.helpers;

import hu.wortex.report.entities.Field;
import hu.wortex.report.entities.ListingDTO;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ValidationUtility {

    private static final String REGEX_PATTERN = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

    private List<String> locationIds;
    private List<Integer> marketplaceIds;
    private List<Integer> listingStatusIds;

    private Map<Integer,String> marketplaces;

    private List<List<String>> csvLines;

    /**
     * validates a JSON parsed DTO
     * collects lines with errors
     * @param listing a listing dto that is about to be saved
     * @return valid or not
     */
    public boolean isValidListing(ListingDTO listing) {

        if (listing.getId() == null || !listing.getId().matches(REGEX_PATTERN)) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.ID);
            return false;
        } else if (listing.getTitle() == null) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.TITLE);
            return false;
        } else if (listing.getDescription() == null) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.DESCRIPTION);
            return false;
        } else if (listing.getLocationId() == null || !locationIds.contains(listing.getLocationId())) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.LOCATION_ID);
            return false;
        } else if (listing.getListingPrice() == null || listing.getListingPrice() < 0 || !precisionCheck(listing.getListingPrice())) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.LISTING_PRICE);
            return false;
        } else if (listing.getCurrency() == null || listing.getCurrency().length() != 3) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.CURRENCY);
            return false;
        } else if (listing.getQuantity() == null || listing.getQuantity() < 0) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.QUANTITY);
            return false;
        } else if (listing.getListingStatusId() == null || !listingStatusIds.contains(listing.getListingStatusId())) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.LISTING_STATUS);
            return false;
        } else if (listing.getMarketPlaceId() == null || !marketplaceIds.contains(listing.getMarketPlaceId())) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.MARKETPLACE);
            return false;
        } else if (listing.getOwnerEmailAddress() == null || !isValidEmail(listing.getOwnerEmailAddress())) {
            appendCsvLines(listing.getId(), listing.getMarketPlaceId(), Field.LISTING_OWNER_EMAIL);
            return false;
        }
        return true;
    }

    /**
     *  trims and validates an email address
     * @param email
     * @return is valid or not
     */
    private boolean isValidEmail(String email) {
        email = email.trim();
        EmailValidator eValidator = EmailValidator.getInstance();
        return eValidator.isValid(email);
    }

    public ValidationUtility(List<String> locationIds,
                             List<Integer> marketplaceIds,
                             List<Integer> listingStatusIds,
                             Map<Integer,String> marketplaces) {

        this.locationIds = locationIds;
        this.listingStatusIds = listingStatusIds;
        this.marketplaceIds = marketplaceIds;
        this.marketplaces = marketplaces;
        csvLines = new ArrayList<>();
    }

    public List<List<String>> getCsvLines() {
        return csvLines;
    }

    /**
     * We want to decide wheter its precision is less then 2 or not
     * @param d
     * @return is valid or not
     */
    private boolean precisionCheck(Double d) {
        if (d!=null) {
            String text = Double.toString(Math.abs(d));
            int integerPlaces = text.indexOf('.');
            int decimalPlaces = text.length() - integerPlaces - 1;
            return decimalPlaces<=2;
        }
        return false;
    }

    /**
     *  adds a List<String> that represents a row in the CSV
     * @param id Listingid
     * @param marketplaceId
     * @param field field name with error
     */
    private void appendCsvLines(String id, Integer marketplaceId, Field field) {
        String marketplaceName = marketplaces.get(marketplaceId);
        csvLines.add(Arrays.asList(id, marketplaceName, field.getFieldName()));
    }

    //
    private void appendCsvLinesWithFieldValue(String id, Integer marketplaceId, Field field, String value) {
        String marketplaceName = marketplaces.get(marketplaceId);
        csvLines.add(Arrays.asList(id, marketplaceName, field.getFieldName(),value));
    }
}
