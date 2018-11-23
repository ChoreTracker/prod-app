package prodapp;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/{userId}")
	public Optional<User> findOneUser(@PathVariable long userId){
		return userRepo.findById(userId);
	}
	
	@RequestMapping("/{userId}/missions")
	public Collection<Mission> findMissionsForUser(@PathVariable long userId){
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		return missionRepo.findByUsersContains(user);
	}
	
	@RequestMapping(path="/{userId}/missions/{missionId}/done", method = RequestMethod.POST)
	public String markComplete(@PathVariable long userId, @RequestParam long missionId, Model model) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.markComplete();
		missionRepo.save(mission);
		Optional<User> user = userRepo.findById(userId);
		
		model.addAttribute("user", user.get());
		return "partials/mission-complete";
	}
	
	
	
	
	
	
	
	
	
	
}
