package prodapp;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(classes = User.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserControllerTest {
	
	@InjectMocks
	private UserController underTest;

	@Mock
	private UserRepository userRepo;
	
	@Mock
	private MissionRepository missionRepo;

	@Mock
	private User user;

	@Mock
	private User user2;

	@Mock
	private Model model;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void shouldAddSingleUserToModel() throws userNotFoundException {
		long arbitraryUserId = 1;
		when(userRepo.findById(arbitraryUserId)).thenReturn(Optional.of(user));
		underTest.findOneUser(arbitraryUserId, model);
		verify(model).addAttribute("user", user);
	}
	
	@Test
	public void shouldAddAllSectorsToUser() {
		Collection<User> allUsers = Arrays.asList(user, user2);
		when(userRepo.findAll()).thenReturn(allUsers);
		underTest.findAllUsers(model);
		verify(model).addAttribute("users", allUsers);
	}

}
