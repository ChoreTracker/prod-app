package prodapp;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
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

	@Resource
	SectorRepository sectorRepo;

	@RequestMapping("/show-users")
	public String findAllUsers(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "users";
	}

	@RequestMapping("/user")
	public String findOneUser(@RequestParam(value = "id") long userId, Model model) throws userNotFoundException {
		Optional<User> userResult = userRepo.findById(userId);

		if (userResult.isPresent()) {

			User user = userResult.get();
			model.addAttribute("user", user);
			model.addAttribute("usersMissions",
					missionRepo.findByUsersContainsAndCompletionDateIsNullAndRecurringIsFalseOrderByDueDate(user));
			model.addAttribute("sectors", sectorRepo.findAll());
			model.addAttribute("unassignedUserMissions",
					missionRepo.findAllByUsersIsNullAndCompletionDateIsNullAndRecurringIsFalse());
			model.addAttribute("allMissions", missionRepo.findAllByOrderByCompletionDateAscDueDateAsc());

			return "user";

		}
		throw new userNotFoundException();
	}

	public Collection<Mission> showAllRecentMissions() {
		Collection<Mission> allMissions = missionRepo.findByRecurring(false);
		Collection<Mission> allRecentMissions = new HashSet<>();
		for (Mission mission : allMissions) {
			if (mission.getCompletionDate() != null) {
				LocalDate doneDate = LocalDate.parse(mission.getCompletionDate());
				if (doneDate.isAfter(LocalDate.now().minusMonths(1))) {
					allRecentMissions.add(mission);
				}
			} else {
				allRecentMissions.add(mission);
			}
		}
		return allRecentMissions;
	}

	@RequestMapping("/setup/users")
	public String addNewUser(Model model) {

		return "setup-users";
	}

	@RequestMapping("/add-user-button")
	public String addUser(String userName, String password, String contact, String roles) {
		User user = new User(userName, password, contact, "default", 0, "USER" + roles);
		userRepo.save(user);
		return "redirect:/show-users";
	}
	
	@RequestMapping("/change-theme")
	public String changeTheme(String theme, Principal principal) {
		String activeUser = principal.getName().toString();
		Optional<User> loggedInUser = userRepo.findByUserName(activeUser);
		User user = loggedInUser.get();
		user.setTheme(theme);
		return "redirect:/";
	}

}
