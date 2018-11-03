package prodapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class MisionTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private UserRepository userRepo;
	
	@Resource
	private MissionRepository missionRepo;
	
	@Test
	public void shouldSaveAndLoadNewMission() {

		User user = new User("Name", "contact");
		userRepo.save(user);
		Mission mission = new Mission("MissionName", "description", "period", "snooze", "dueDate", "completionDate", user);
		missionRepo.save(mission);
		long missionId = mission.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission missionResult = result.get();
		assertThat(missionResult.getMissionName(), is("MissionName"));
		
		
	}

}
