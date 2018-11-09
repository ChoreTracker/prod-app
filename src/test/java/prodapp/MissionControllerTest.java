package prodapp;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class MissionControllerTest {
	
	@InjectMocks
	private MissionController underTest;

	@Mock
	private MissionRepository missionRepo;

	@Mock
	private Mission mission;
	long missionId;
	
	@Mock
	private Mission mission2;
	
	@Mock
	private User user;
	long userId;
	
	@Mock 
	private User user2;
	
	@Mock
	private SectorRepository sectorRepo;
	
	@Mock
	private UserRepository userRepo;

	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleMissionToModel() throws missionNotFoundException {
		long arbitraryMissionId = 1;
		when(missionRepo.findById(arbitraryMissionId)).thenReturn(Optional.of(mission));
		underTest.findOneMission(arbitraryMissionId, model);
		verify(model).addAttribute("mission", mission);

	}
	
	@Test
	public void shouldAddAllMissionsToModel() {
		Collection<Mission> allMissions = Arrays.asList(mission, mission2);
		when(missionRepo.findAll()).thenReturn(allMissions);
		underTest.findAllMissions(model);
		verify(model).addAttribute("missions", allMissions);
	}
	
	@Test
	public void shouldCreateNewMission() {
		underTest.createMission("MissionName3", "description3", "period3", "snooze3", "dueDate3", "completionDate3", false, user, user2);
		ArgumentCaptor<Mission> missionArgument = ArgumentCaptor.forClass(Mission.class);
		verify(missionRepo).save(missionArgument.capture());
		assertEquals("MissionName3", missionArgument.getValue().getMissionName());
	}
	
	@Test
	public void shouldDeleteAMission() {
		missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(mission));
		underTest.deleteMissionById(missionId);
		verify(missionRepo).deleteById(missionId);
	}
	
	@Test
	public void shouldAssignAMissionToAUser() {
		missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(mission));
		Collection<User> users = new HashSet<>();
		when(mission.getUsers()).thenReturn(users);
		users.add(user);
		userId = 2;
		when(userRepo.findById(userId)).thenReturn(Optional.of(user));
		underTest.assignMissionToUserById(missionId, userId);
		verify(mission).addUser(user);
		assertThat(mission.getUsers(), contains(user));
		
	}
	
	@Test
	public void shouldRemoveAUserFromAMission() {
		missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(mission));
		Collection<User> users = new HashSet<>();
		users.add(user);
		userId = 2;
		when(mission.getUsers()).thenReturn(users);
		when(userRepo.findById(userId)).thenReturn(Optional.of(user));
		underTest.assignMissionToUserById(missionId, userId);
		underTest.removeUserFromMission(missionId, userId);
		users.remove(user);
		verify(mission).removeUser(user);
		assertThat(mission.getUsers().size(), is(0));	
	}
	
	@Test
	public void shouldShowAllUnassignedMissions() {
		userRepo.save(user);
		userRepo.save(user2);
		Mission mission1 = new Mission("MissionName", "description", "period", "snooze", "dueDate", "completionDate", true);
		Mission mission2 = new Mission("MissionName2", "description2", "period2", "snooze2", "dueDate2", "completionDate2", true,
				user);
		Mission mission3 = new Mission("MissionName3", "description3", "period3", "snooze3", "dueDate3", "completionDate3", false,
				user, user2);
		missionRepo.save(mission1);
		missionRepo.save(mission2);
		missionRepo.save(mission3);
		Collection<Mission> allMissions = Arrays.asList(mission1, mission2, mission3);
		when(missionRepo.findAll()).thenReturn(allMissions);
		underTest.findUnassignedMissions(model);
		Collection<Mission> expected = new HashSet<>();
		expected.add(mission1);
		verify(model).addAttribute("missions", expected);
	}
	
	
}
