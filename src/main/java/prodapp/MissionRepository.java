package prodapp;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {

	Collection<Mission> findByUsersContains(User user);

	void deleteBySector(Long sectorId);

//	Collection<Mission> findAllByUsersAndRecurringIsFalseOrderByDueDate(User user);

	//find user's missions for a given completion date, ordered by due date
	Collection<Mission> findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(User user, String completionDate);

	//find user's missions ordered by due date
	Collection<Mission> findAllByUsersAndRecurringIsFalseOrderByDueDate(User user);
	
	
	//find missions with no assigned users
	Collection<Mission> findAllByUsersIsNullAndRecurringIsFalse();

	//finds missions that are or are not recurring
	Collection<Mission> findByRecurring(boolean recurring);

	//user's finished missions, ordered by due date
	Collection<Mission> findCompletionDateNotNullByUsersIdOrderByDueDate(long userId);
	
	//all unfinished missions in a sector, ordered by due date
	Collection<Mission> findAllBySectorAndCompletionDateNotNullOrderByDueDate(long sectorId);

	Collection<Mission> findAllBySector(Sector sector);

	Collection<Mission> findByUsersContainsAndCompletionDateIsNull(User user);
	
	Collection<Mission> findAllByUsersIsNullAndCompletionDateIsNullAndRecurringIsFalse();
	
	Collection<Mission> findByUsersContainsAndCompletionDateIsNullOrderByDueDate(User user);

	Collection<Mission> findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(Optional<User> user,
			String completionDate);


	Collection<Mission> findByUsersContainsAndCompletionDateIsNullAndRecurringIsFalseOrderByDueDate(User user);

	Collection<Mission> findAllByUsersAndRecurringIsFalseOrderByDueDate(String loggedInUser);

	Collection<Mission> findAllBySectorAndRecurringIsFalse(Sector sectorResult);
	
	Collection<Mission> findByCompletionDateIsNotNullOrderByUsers();
	
	Collection<Mission> findAllByOrderByCompletionDateAscDueDateAsc();

	
//	Collection<Mission> findAllRecurringIsTrue();
}
