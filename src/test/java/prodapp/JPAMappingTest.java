package prodapp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
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

public class JPAMappingTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private UserRepository userRepo;

	@Resource
	private MissionRepository missionRepo;

	@Resource
	private SectorRepository sectorRepo;

	User user = new User("Name", "contact");
	Mission mission = new Mission("MissionName", "description", "period", "snooze", "dueDate", "completionDate", user);
	Mission mission2 = new Mission("MissionName2", "description2", "period2", "snooze2", "dueDate2", "completionDate2",
			user);
	Mission mission3 = new Mission("MissionName3", "description3", "period3", "snooze3", "dueDate3", "completionDate3",
			user);

	@Test
	public void shouldSaveAndLoadNewMission() {
		userRepo.save(user);
		missionRepo.save(mission);

		long missionId = mission.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Mission> result = missionRepo.findById(missionId);
		Mission missionResult = result.get();
		assertThat(missionResult.getMissionName(), is("MissionName"));

	}

	@Test
	public void shouldBeAbleToAddAUser() {
		userRepo.save(user);
		missionRepo.save(mission);
		long userId = user.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<User> result = userRepo.findById(userId);
		User userResult = result.get();
		assertThat(user.getUserName(), is("Name"));
	}

	@Test
	public void shouldAddMissionToSector() {
		userRepo.save(user);
		missionRepo.save(mission);
		Sector sector = new Sector("sectorName", mission);
		sectorRepo.save(sector);
		long sectorId = sector.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Sector> result = sectorRepo.findById(sectorId);
		Sector sectorResult = result.get();
		assertThat(sectorResult.getMissions(), contains(mission));
		assertThat(mission.getMissionName(), is("MissionName"));
	}

//	@Test
//	public void shouldFindMissionsByUser() {
//		userRepo.save(user);
//		missionRepo.save(mission);
//		missionRepo.save(mission3);
//
//		entityManager.flush();
//		entityManager.clear();
//
//		Collection<Mission> result = userRepo.findByUser(user);
//
//		assertThat(result, containsInAnyOrder(mission, mission3));
//
//	}

	// pull the 2 missions, check their userIds, get id of user & see if they match

}
