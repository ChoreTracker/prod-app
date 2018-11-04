package prodapp;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@Resource
	UserRepository userRepo;
	
	@RequestMapping("/user")
	public String findOneUser(long userId, Model model) throws userNotFoundException {
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isPresent()) {
			model.addAttribute("users", user.get());
			return "user";
		}
		throw new userNotFoundException();

		
	}
	@RequestMapping("/show-users")
	public String findAllUsers(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "users";
		
	}

}
