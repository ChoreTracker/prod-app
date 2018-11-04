package prodapp;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MissionController {
	
	@Resource
	MissionRepository missionRepo;

	@RequestMapping("/mission")
	public String findOneMission(long missionId, Model model) throws missionNotFoundException {
		Optional<Mission> mission = missionRepo.findById(missionId);
		
		if (mission.isPresent()) {
			model.addAttribute("missions", mission.get());
			return "mission";
		}
		
		throw new missionNotFoundException();
		
		
	}

	@RequestMapping("/show-missions")
	public String findAllMissions(Model model) {
		model.addAttribute("missions", missionRepo.findAll());
		return "missions";
		
		
	}

}
