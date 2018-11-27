package prodapp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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

	User user = new User("Name", "password", "contact", "ADMIN");
	User user2 = new User("Name2", "password2", "contact2", "USER");
	Sector sector = new Sector("sectorName", "sectorUrl");
	Sector sector2 = new Sector("othersectorName", "sector2Url");
	Mission mission = new Mission("MissionName", "description", sector, 5, 0, "dueDate", "completionDate",true, 0, user);
	Mission mission2 = new Mission("MissionName2", "description2", sector, 6, 0, "dueDate2", "completionDate2", true, 0, 
			user);
	Mission mission3 = new Mission("MissionName3", "description3",sector2,  7, 0, "dueDate3", "completionDate3", false, 0, 
			user, user2);

	@Test
	public void shouldSaveAndLoadNewMission() {
		userRepo.save(user);
		sectorRepo.save(sector);
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
		sectorRepo.save(sector);
		missionRepo.save(mission);
		long userId = user.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<User> result = userRepo.findById(userId);
		User userResult = result.get();
		assertThat(userResult.getUserName(), is("Name"));
	}

//	@Test
//	public void shouldAddMissionToSector() {
//		userRepo.save(user);
//		missionRepo.save(mission);
//		Sector sector = new Sector("sectorName", "");
//		sectorRepo.save(sector);
//		long sectorId = sector.getId();
//
//		entityManager.flush();
//		entityManager.clear();
//
//		Optional<Sector> result = sectorRepo.findById(sectorId);
//		Sector sectorResult = result.get();
//		assertThat(sectorResult.getMissions(), contains(mission));
//		assertThat(mission.getMissionName(), is("MissionName"));
//	}

	@Test
	public void shouldFindMissionsByUser() {
		userRepo.save(user);
		sectorRepo.save(sector);
		sectorRepo.save(sector2);
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
		sectorRepo.save(sector);
		Mission mission3 = new Mission("MissionName3", "description3",sector, 4, 0, "dueDate3",
				"completionDate3", true, 0, user, user2);
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
		sectorRepo.save(sector);
		
		missionRepo.save(mission);
		missionRepo.save(mission2);
	
		

		entityManager.flush();
		entityManager.clear();

		Collection<Mission> result = missionRepo.findAllBySector(sector);

		assertThat(result, containsInAnyOrder(mission, mission2));
	}

	@Test
	public void shouldFindAllMissionsAndSortByDueDate() {
		userRepo.save(user);
		userRepo.save(user2);
		sectorRepo.save(sector);
		sectorRepo.save(sector2);
		Mission mission = new Mission("MissionName", "description",sector, 3, 0, "2018-1-3", "completionDate", false, 0, user);
		Mission mission2 = new Mission("MissionName2", "description2", sector, 4, 0, "2018-1-1", "completionDate2", false, 0, user);
		Mission mission3 = new Mission("MissionName3", "description3",sector2, 0, 0, "2018-1-15", "completionDate3", false, 0,
				user2);
		missionRepo.save(mission);
		missionRepo.save(mission2);
		missionRepo.save(mission3);

		entityManager.flush();
		entityManager.clear();

		Collection<Mission> sortedMissions = missionRepo.findAllByUsersAndRecurringIsFalseOrderByDueDate(user);
		assertThat(sortedMissions, contains(mission2, mission));

	}

	@Test
	public void shouldFindAllIncompleteMissionsAndSortByDueDate() {
		userRepo.save(user);
		userRepo.save(user2);
		sectorRepo.save(sector);
		Mission mission = new Mission("MissionName", "description", sector, 3, 0, "2018-1-3", "", false, 0, user);
		Mission mission2 = new Mission("MissionName2", "description2", sector, 4, 0, "2018-1-1", "", false, 0, user);
		Mission mission3 = new Mission("MissionName3", "description3", sector, 0, 0, "2018-1-15", "completionDate3", false, 0,
				user2);
		missionRepo.save(mission);
		missionRepo.save(mission2);
		missionRepo.save(mission3);

		entityManager.flush();
		entityManager.clear();

		Collection<Mission> sortedMissions = missionRepo.findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(user, "");
		assertThat(sortedMissions, contains(mission2, mission));

	}

	@Test
	public void shouldClaimUnassignedMission() {
		userRepo.save(user);
		sectorRepo.save(sector);
		Mission mission = new Mission("MissionName", "description", sector, 3, 0, "2018-1-3", "", false, 0);
		missionRepo.save(mission);
		Mission mission2 = new Mission("MissionName", "description", sector, 3, 0, "2018-1-3", "", false,0, user);
		missionRepo.save(mission2);
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Mission> unassignedMissions = missionRepo.findAllByUsersIsNullAndRecurringIsFalse();
		assertThat(unassignedMissions, containsInAnyOrder(mission));
		mission.addUser(user);
		missionRepo.save(mission);
		
		Collection<Mission> unassignedMissions2 = missionRepo.findAllByUsersIsNullAndRecurringIsFalse();
		assertThat(unassignedMissions2, not(containsInAnyOrder(mission)));
		
	}
}
