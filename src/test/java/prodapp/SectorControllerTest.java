package prodapp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
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
	public void shouldAssignAllMissionsInSectorToUser() {
		missionRepo.save(mission);
		missionRepo.save(anotherMission);
		userRepo.save(user1);
		long sectorId = 1;
		long userId = 2;
		when(userRepo.findById(userId)).thenReturn(Optional.of(user1));
		Sector sector = new Sector("sector name", mission, anotherMission);
		sectorRepo.save(sector);
		when(sectorRepo.findById(sectorId)).thenReturn(Optional.of(sector));
		underTest.assignAllMissionsInSectorToUserById(sectorId, userId);
		Collection<User> userResult = mission.getUsers();
		assertThat(userResult.size(), is(1));
		assertThat(userResult, contains(user1));
		
	}
	
	//put the mission in the sector
	
	
	
	
	
	
	
	
}

