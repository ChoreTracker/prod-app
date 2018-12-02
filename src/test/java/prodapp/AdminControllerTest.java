package prodapp;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
	    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    long userId = user.getId();
		userRepo.save(user);
		return userId;
		
	}

}
