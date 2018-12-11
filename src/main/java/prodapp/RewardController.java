package prodapp;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RewardController {

	@Resource
	UserRepository userRepo;

	@Resource
	MissionRepository missionRepo;
	
	@Resource
	RewardRepository rewardRepo;
	
	@RequestMapping("/show-rewards")
	public String findAllRewards(Model model) {
		model.addAttribute("rewards", rewardRepo.findAll());
		return "rewards";
	}
	
	
	
}
