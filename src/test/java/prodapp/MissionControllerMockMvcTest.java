package prodapp;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@WebMvcTest(MissionController.class)
@ContextConfiguration(locations = {
	    "classpath:spring/applicationContext.xml",
	    "classpath:spring/applicationContext-jpa.xml",
	    "classpath:spring/applicationContext-security.xml" })

//@ContextConfiguration(classes={MissionController.class, SecurityConfig.class})
public class MissionControllerMockMvcTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	 
	@Before
	public void setup() {
	     mvc = MockMvcBuilders
	            .webAppContextSetup(context)
	            .apply(springSecurity())
	            .build();
	    }
	
	
	@Mock
	private Mission firstMission;
	
	@Mock
	private Mission secondMission;

	@MockBean
	private MissionRepository missionRepo;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private SectorRepository sectorRepo;
	
	
	@Mock
	private Sector sector;
	
	@Test
	public void shouldBeOkForAllMissions() throws Exception {
		mvc.perform(get("/show-missions")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToAllMissionsView() throws Exception {
		mvc.perform(get("/show-missions")).andExpect(view().name(is("missions")));
	}
	
	@Test
	public void shouldPutAllMissionsIntoModel() throws Exception {
		Collection<Mission> allMissions = asList(firstMission, secondMission);
		when(missionRepo.findAll()).thenReturn(allMissions);
		mvc.perform(get("/show-missions")).andExpect(model().attribute("missions", is(allMissions)));
	}
	
	
	@Test
	public void shouldBeOkForSingleMission() throws Exception {
		when(missionRepo.findById(1L)).thenReturn(Optional.of(firstMission));
		mvc.perform(get("/mission?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleMissionView() throws Exception {
		long missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(firstMission));
		mvc.perform(get("/mission?id=1")).andExpect(view().name(is("mission")));
	}
	
	@Test
	public void shouldPutASingleMissionIntoModel() throws Exception {
		when(missionRepo.findById(1L)).thenReturn(Optional.of(firstMission));

		mvc.perform(get("/mission?id=1")).andExpect(model().attribute("missions", is(firstMission)));
	}
	
}