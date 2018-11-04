package prodapp;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {

	Collection<Mission> findByUsersContains(User user);



}
