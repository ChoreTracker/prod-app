package prodapp;

import javax.annotation.Resource;

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

}
