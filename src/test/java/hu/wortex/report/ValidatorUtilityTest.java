package hu.wortex.report;

import hu.wortex.report.entities.ListingDTO;
import hu.wortex.report.helpers.ValidationUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class ValidatorUtilityTest {

    ValidationUtility validationUtility = null;
    ListingDTO listingDTO = null;

    @Before
    public void beforeFunction(){
        List<String> locationIds = Arrays.asList("test1", "test2", "test3");
        List<Integer> marketplaceIds = Arrays.asList(1,2,3);
        List<Integer> listingStatusIds = Arrays.asList(1,2,3);

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "m1");
        map.put(2, "m2");
        map.put(3, "m3");

        validationUtility = new ValidationUtility(locationIds, marketplaceIds, listingStatusIds,map);

        listingDTO = new ListingDTO();

        // setting up a valid DTO
        listingDTO.setId("8fa58528-6556-4c4b-9270-337ff2972188");
        listingDTO.setCurrency("EUR");
        listingDTO.setDescription("Hello there");
        listingDTO.setListingPrice(123.34);
        listingDTO.setListingStatusId(1);
        listingDTO.setLocationId("test1");
        listingDTO.setOwnerEmailAddress("hack@mail.co");
        listingDTO.setQuantity(1);
        listingDTO.setUploadTime(new Date());
        listingDTO.setTitle("General Kenobi");
        listingDTO.setMarketPlaceId(2);
    }

    @Test
    public void fullValidDtoTest() {
        Assert.assertTrue(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void uuidNullTest() {
        listingDTO.setId(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }


    @Test
    public void invalidUuidTest() {

        listingDTO.setId("not valid :(");
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));

    }

    @Test
    public void currencyTooLongTest() {
        listingDTO.setCurrency("TOO LONG");
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void currencyTooShortTest() {
        listingDTO.setCurrency("US");
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void currencyNullTest() {
        listingDTO.setCurrency(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void descriptionNulLTest() {
        listingDTO.setDescription(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void listingPriceNullTest() {
        listingDTO.setListingPrice(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void listingPricePrecisionTest() {
        listingDTO.setListingPrice(123.897123);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void listingStatusIdNotExistTest() {
        listingDTO.setListingStatusId(123);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void listingStatusIdNull() {
        listingDTO.setListingStatusId(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void locationIdNotExist() {
        listingDTO.setLocationId("nope-not-real");
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }


    // since its an apache commons lib it doesn't need heavy testing
    @Test
    public void ownerEmailAddressNotValidTest() {
        listingDTO.setOwnerEmailAddress("@asd.hu");
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void ownerEmailAddressNullTest() {
        listingDTO.setOwnerEmailAddress(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void uploadTimeNotValidDate() {

    }

    @Test
    public void uploadTimeNullTest() {
        listingDTO.setUploadTime(null);
        Assert.assertTrue(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void titleNullTest() {
        listingDTO.setTitle(null);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }

    // According to the documentation for text fields empty string is allowed only NULL is denied
    @Test
    public void titleEmptyStringTest() {
        listingDTO.setTitle("");
        Assert.assertTrue(validationUtility.isValidListing(listingDTO));
    }

    @Test
    public void marketplaceIdNotExist() {
        listingDTO.setMarketPlaceId(123123);
        Assert.assertFalse(validationUtility.isValidListing(listingDTO));
    }





}
