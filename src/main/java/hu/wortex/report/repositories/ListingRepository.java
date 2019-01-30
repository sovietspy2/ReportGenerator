package hu.wortex.report.repositories;

import hu.wortex.report.entities.Listing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ListingRepository extends CrudRepository<Listing, String> {
    @Query("select t from Listing t")
    Stream<Listing> streamAll();
}
