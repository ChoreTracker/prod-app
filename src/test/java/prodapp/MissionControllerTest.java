package prodapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
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
	@Mock
	private Mission mission2;

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
		verify(model).addAttribute("missions", mission);

	}
	
	@Test
	public void shouldAddAllMissionsToModel() {
		Collection<Mission> allMissions = Arrays.asList(mission, mission2);
		when(missionRepo.findAll()).thenReturn(allMissions);
		underTest.findAllMissions(model);
		verify(model).addAttribute("missions", allMissions);
	}
}
