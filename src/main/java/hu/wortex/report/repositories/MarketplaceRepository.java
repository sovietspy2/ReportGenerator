package hu.wortex.report.repositories;

import hu.wortex.report.entities.Location;
import hu.wortex.report.entities.Marketplace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends CrudRepository<Marketplace, Integer> {
}

