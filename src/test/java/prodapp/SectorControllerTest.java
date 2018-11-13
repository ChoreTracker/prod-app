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

public class SectorControllerTest {


	@Mock
	private Sector sector;
	long sectorId;
	
	@Mock
	private Sector sector2;
	
	@Mock
	private Model model;	
	
	@Mock
	private Mission mission;
	
	@Mock 
	private Mission anotherMission;
	
	@Mock
	private Mission mission3;
	
	@Mock
	private User user1;
	long userId;
	
	@Mock
	private SectorRepository sectorRepo;
	
	@Mock
	private MissionRepository missionRepo;
	
	@Mock 
	private UserRepository userRepo;
	
	@InjectMocks
	private SectorController underTest;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleSectorToModel() throws sectorNotFoundException {
		long arbitrarySectorId = 1;
		when(sectorRepo.findById(arbitrarySectorId)).thenReturn(Optional.of(sector));
		underTest.findOneSector(arbitrarySectorId, model);
		verify(model).addAttribute("sector", sector);
	}
	
	@Test
	public void shouldAddAllSectorsToModel() {
		Collection<Sector> allSectors = Arrays.asList(sector, sector2);
		when(sectorRepo.findAll()).thenReturn(allSectors);
		underTest.findAllSectors(model);
		verify(model).addAttribute("sectors", allSectors);
	}

	
	@Test
	public void shouldAddNewSector() {
		underTest.addSector("sectorName");
		ArgumentCaptor<Sector> sectorArgument = ArgumentCaptor.forClass(Sector.class);
		verify(sectorRepo).save(sectorArgument.capture());
		assertEquals("sectorName", sectorArgument.getValue().getSectorName());
	}
	
	@Test
	public void shouldRemoveSectorById() {
		sectorId = 1;
		underTest.deleteSectorById(sectorId);
		verify(sectorRepo).deleteById(sectorId);
		
	}		

	@Test
	public void shouldAddMissionToSector() {
		long missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(mission3));
		sectorId= 1;
		when(sectorRepo.findById(sectorId)).thenReturn(Optional.of(sector));
		Collection<Mission> missions = new HashSet<>();
		missions.add(mission3);
		when(sector.getMissions()).thenReturn(missions);
		underTest.addMissionToSector(sectorId, missionId);
		Collection<Mission> sectorMissions = sector.getMissions();
		assertThat(sectorMissions.size(), is(1));

	}
	
	@Test
	public void shouldAssignAllMissionsInSectorToUser() {
		long missionId2 = 2;
		when(missionRepo.findById(missionId2)).thenReturn(Optional.of(mission));
		long missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(mission3));
		sectorId= 1;
		when(sectorRepo.findById(sectorId)).thenReturn(Optional.of(sector));
		Collection<Mission> missions = new HashSet<>();
		missions.add(mission3);
		missions.add(mission);
		when(sector.getMissions()).thenReturn(missions);
		underTest.addMissionToSector(sectorId, missionId);
		underTest.addMissionToSector(sectorId, missionId2);
		Collection<User> users = new HashSet<>();
		when(mission.getUsers()).thenReturn(users);
		Collection<User> users2 = new HashSet<>();
		when(mission3.getUsers()).thenReturn(users2);
		userId = 3;
		users.add(user1);
		users2.add(user1);
		when(userRepo.findById(userId)).thenReturn(Optional.of(user1));
		underTest.assignAllMissionsInSectorToUserById(sectorId, userId);
		assertThat(mission.getUsers(), contains(user1));
		assertThat(mission3.getUsers(), contains(user1));
	}

}

