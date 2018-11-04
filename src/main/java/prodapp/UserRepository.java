package prodapp;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

//	Collection<Mission> findByUser(User user);

}
