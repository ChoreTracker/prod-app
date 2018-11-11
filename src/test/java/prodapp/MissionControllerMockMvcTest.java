package prodapp;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;




@RunWith(SpringRunner.class)
@WebMvcTest(MissionController.class)
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
	
	@MockBean
	private MissionRepository missionRepo;
	
	@Mock
	private Mission mission;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private SectorRepository sectorRepo;
	
	@Mock
	private Sector sector;
	
	@Test
	public void shouldRouteToSingleMissionView() throws Exception {
		long missionId = 1;
		when(missionRepo.findById(missionId)).thenReturn(Optional.of(mission));
		mvc.perform(get("/mission?id=1")).andExpect(view().name(is("mission")));
		
	}
	
}