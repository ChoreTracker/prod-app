package prodapp;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.context.WebApplicationContext;

public class AdminControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	private UserRepository userRepo;
	
	@WithMockUser (username="spring", password="password", roles="USER")
	public long shouldReturnLoggedInUserID() {
		User user1 = new User ("me", "password", "ADMIN");
		userRepo.save(user1);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		long userId = user.getId();
		System.out.print(userId);
		return userId;
		
	}

}
