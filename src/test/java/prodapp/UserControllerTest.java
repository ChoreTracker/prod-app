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


public class UserControllerTest {
	
	@InjectMocks
	private UserController underTest;

	@Mock
	private UserRepository userRepo;

	@Mock
	private User user;

	@Mock
	private User user2;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleUserToModel() throws userNotFoundException {
		long arbitraryUserId = 1;
		when(userRepo.findById(arbitraryUserId)).thenReturn(Optional.of(user));
		underTest.findOneUser(arbitraryUserId, model);
		verify(model).addAttribute("users", user);
	}
	
	@Test
	public void shouldAddAllSectorsToUser() {
		Collection<User> allUsers = Arrays.asList(user, user2);
		when(userRepo.findAll()).thenReturn(allUsers);
		underTest.findAllUsers(model);
		verify(model).addAttribute("users", allUsers);
	}

}
