package hu.wortex.report.repositories;

import hu.wortex.report.entities.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Listing, String> {
}
