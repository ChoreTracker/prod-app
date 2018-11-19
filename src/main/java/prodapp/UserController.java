package prodapp;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Resource
	UserRepository userRepo;
	
	@Resource
	MissionRepository missionRepo;
	
	@RequestMapping("/show-users")
	public String findAllUsers(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "users";
	}

	@RequestMapping("/user")
	public String findOneUser(@RequestParam(value="id") long userId, Model model) throws userNotFoundException {
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isPresent()) {
			model.addAttribute("user", user.get());
			model.addAttribute("unassignedUserMissions", missionRepo.findAllByUsersIsNullAndRecurringIsFalse());
			model.addAttribute("allUserMissions", missionRepo.findAll());
			return "user";
			
		}
		throw new userNotFoundException();
	}

	
	@RequestMapping("/setup/users")
	public String addNewUser (Model model) {
	
		return "setup-users";
}
}
