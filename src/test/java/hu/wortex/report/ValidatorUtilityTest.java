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
    public void testValidator() {

        Assert.assertTrue(validationUtility.isValidListing(listingDTO));

        //validationUtility.isValidListing()
    }


}
