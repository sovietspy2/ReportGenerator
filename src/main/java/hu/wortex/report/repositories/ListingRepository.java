package hu.wortex.report.repositories;

import hu.wortex.report.entities.Listing;
import hu.wortex.report.entities.Marketplace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ListingRepository extends CrudRepository<Listing, String> {
    @Query("select t from Listing t")
    Stream<Listing> streamAll();

    Integer countAllByMarketPlace(Marketplace marketplace);

    @Query(value = "SELECT AVG(e.listing_price) FROM listing e WHERE e.marketplace =:marketplace",nativeQuery = true)
    Double findAverageCountNumberByMarketPlace(@Param("marketplace") Integer marketplace);

    @Query(value = "SELECT SUM(e.listing_price) FROM listing e WHERE e.marketplace =:marketplace",nativeQuery = true)
    Double findListingPriceSumByMarketplace(@Param("marketplace") Integer marketplace);

    @Query(value = "SELECT listing.owner_email_address FROM listing GROUP BY listing.owner_email_address ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    String findBestOwnerEmailAddress();


}
