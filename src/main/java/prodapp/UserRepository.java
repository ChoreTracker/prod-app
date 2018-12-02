package prodapp;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Collection<User> findByMissionsContains(Mission mission);

	Optional<User> findByUserName(String userName);
	

}
