package prodapp;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {

	Collection<Mission> findByUsersContains(User user);

	void deleteBySector(Long sectorId);

	Collection<Mission> findAllByUsersAndRecurringIsFalseOrderByDueDate(User user);

	Collection<Mission> findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(User user, String completionDate);

	Collection<Mission> findAllByUsersIsNullAndRecurringIsFalse();

	Collection<Mission> findByRecurring(boolean recurring);

}
