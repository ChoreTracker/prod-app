package prodapp;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AdminController {
	
	@Resource
	MissionRepository missionRepo;

	@Resource
	UserRepository userRepo;
	
	@Resource
	SectorRepository sectorRepo;
	
	@RequestMapping ("/admin")
	public String admin(Model model) {
		model.addAttribute("sectors", sectorRepo.findAll());
		model.addAttribute("missions", missionRepo.findAll());
		model.addAttribute("users", userRepo.findAll());
		return "admin";
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	public String showAllUnassignedMissions(Model model) {
		Collection<Mission> unassignedMissions = missionRepo.findAllByUsersIsNullAndRecurringIsFalse();
		model.addAttribute("missions", unassignedMissions);
		return "missions";
	}
	
	public String sortUserIncompleteMissionsByDueDate(Model model) {
		User loggedInUser = findLoggedInUser();

		Collection<Mission> foundMissions = missionRepo.findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(loggedInUser, "");
		model.addAttribute("missions", foundMissions);
		return "missions";
	}
	private User findLoggedInUser() {
		Object activeUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User loggedInUser = (User) activeUser;
		return loggedInUser;
	}

}
