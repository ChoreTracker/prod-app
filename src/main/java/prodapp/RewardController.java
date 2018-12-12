package prodapp;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/cash-in-reward-button")
	public String cashInReward(@RequestParam long rewardId, @RequestParam  long userId) {
		Optional<User>userResult = userRepo.findById(userId);
		User user = userResult.get();
		int userBalance = user.getRewardBalance();
		Optional<Reward>rewardResult = rewardRepo.findById(rewardId);
		Reward reward = rewardResult.get();
		int rewardCost = reward.getRewardValue();
		if (rewardCost < userBalance) {
		user.setRewardBalance(userBalance - rewardCost);
		userRepo.save(user);
		}
		return "redirect:/user?id=" + userId;
		
	}
	
	
}
