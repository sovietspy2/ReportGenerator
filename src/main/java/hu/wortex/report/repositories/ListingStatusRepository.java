package hu.wortex.report.repositories;

import hu.wortex.report.entities.ListingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingStatusRepository extends CrudRepository<ListingStatus, Integer> {
}
