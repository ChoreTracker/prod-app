package prodapp;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Resource
	private MissionRepository missionRepo;
	
	@Resource
	private UserRepository userRepo;
	
	@Resource
	private SectorRepository sectorRepo;
	
	@RequestMapping("")
	public Iterable<User> findAllUsers(){
		return userRepo.findAll();	
	}
	
	@RequestMapping("/{id}")
	public Optional<User> findOneUser(@PathVariable long userId){
		return userRepo.findById(userId);
	}
	
	@RequestMapping("/{id}/missions")
	public Collection<Mission> findMissionsForUser(@PathVariable long userId){
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		return missionRepo.findByUsersContains(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
