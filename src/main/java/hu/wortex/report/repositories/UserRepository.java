package hu.wortex.report.repositories;

import hu.wortex.report.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
