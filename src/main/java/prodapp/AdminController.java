package prodapp;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AdminController {
	
	@Resource
	MissionRepository missionRepo;

	@Resource
	UserRepository userRepo;
	
	@Resource
	SectorRepository sectorRepo;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView projectBase(Model model, Principal principal) {
		String loggedUser = principal.getName().toString();
		Optional<User> user = userRepo.findByUserName(loggedUser);
		long userId = user.get().getId();
	    return new ModelAndView("redirect:/user?id=" + userId);
	}
	
	@RequestMapping ("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("sectors", sectorRepo.findAll());
        model.addAttribute("missions", missionRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("completeMissions", missionRepo.findByCompletionDateIsNotNullOrderByUsers());
        String activeUser = principal.getName();
        Optional<User> userTheme = userRepo.findByUserName(activeUser);
        User loggedInUser = userTheme.get();
        model.addAttribute("loggedInUser", loggedInUser);
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
	
	public String sortUserIncompleteMissionsByDueDate(Model model,Principal principal) {
		String loggedInUser = principal.getName().toString();
		Optional<User> user = userRepo.findByUserName(loggedInUser);
		Collection<Mission> foundMissions = missionRepo.findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(user, "");
		model.addAttribute("missions", foundMissions);
		return "missions";
	}
//	private Optional<User> findLoggedInUser(Model model, Principal principal) {
//		String loggedInUser = principal.getName().toString();
//		Optional<User> user = userRepo.findByUserName(loggedInUser);
//		return user;
//	}

}
