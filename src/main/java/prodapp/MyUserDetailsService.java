package prodapp;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
 
    @Resource
    private UserRepository userRepo;
 
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user;
		Optional<User> existingUser = userRepo.findByUserName(userName);
		if (!existingUser.isPresent()) {
			throw new UsernameNotFoundException("User not found.");
		}
		user = existingUser.get();
		
		UserBuilder builder;
		builder = org.springframework.security.core.userdetails.User.withUsername(userName);
		builder.password(user.getPassword());
		builder.roles(user.getRoles());
		

		return builder.build();
	}
	
}
