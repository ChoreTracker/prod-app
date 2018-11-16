package prodapp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Collection;
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
	User user2 = new User("Name2", "contact2");
	Mission mission = new Mission("MissionName", "description", "period", 0, "dueDate", "completionDate",true, user);
	Mission mission2 = new Mission("MissionName2", "description2", "period2", 0, "dueDate2", "completionDate2", true,
			user);
	Mission mission3 = new Mission("MissionName3", "description3", "period3", 0, "dueDate3", "completionDate3", false,
			user, user2);

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
		assertThat(userResult.getUserName(), is("Name"));
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

	@Test
	public void shouldFindMissionsByUser() {
		userRepo.save(user);
		missionRepo.save(mission);
		missionRepo.save(mission3);

		entityManager.flush();
		entityManager.clear();

		Collection<Mission> result = missionRepo.findByUsersContains(user);

		assertThat(result, containsInAnyOrder(mission, mission3));
	}

	@Test
	public void shouldFindUsersByMission() {
		userRepo.save(user);
		userRepo.save(user2);
		Mission mission3 = new Mission("MissionName3", "description3", "period3", 0, "dueDate3",
				"completionDate3", true, user, user2);
		missionRepo.save(mission3);

		entityManager.flush();
		entityManager.clear();

		Collection<User> result = userRepo.findByMissionsContains(mission3);

		assertThat(result.size(), is(2));
		assertThat(result, containsInAnyOrder(user, user2));
	}

	@Test
	public void shouldFindMissionsBySector() {
		userRepo.save(user);
		missionRepo.save(mission);
		missionRepo.save(mission2);
		Sector sector = new Sector("sectorName", mission, mission2);
		sectorRepo.save(sector);

		entityManager.flush();
		entityManager.clear();

		Collection<Mission> result = sector.getMissions();

		assertThat(result, containsInAnyOrder(mission, mission2));
	}

}
