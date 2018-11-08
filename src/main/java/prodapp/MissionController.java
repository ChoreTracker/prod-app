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
	
	@Resource
	UserRepository userRepo;

	
	//all info on one mission is available; returns the single mission page
	@RequestMapping("/mission")
	public String findOneMission(long missionId, Model model) throws missionNotFoundException {
		Optional<Mission> mission = missionRepo.findById(missionId);
		
		if (mission.isPresent()) {
			model.addAttribute("mission", mission.get());
			return "mission";
		}
		throw new missionNotFoundException();	
	}
	
	//shows all the missions, regardless of sector; returns the all missions page
	@RequestMapping("/show-missions")
	public String findAllMissions(Model model) {
		model.addAttribute("missions", missionRepo.findAll());
		return "missions";	
	}
	
	//button to create a mission, doesn't add it to a sector--("/add-mission-to-sector-button") does that; returns the all missions page
	@RequestMapping("/create-mission-button")
	public String createMission(String missionName, String missionDescription, String period, String snooze, String dueDate,
			String completionDate, boolean recurring, User...users) {
		missionRepo.save(new Mission(missionName, missionDescription, period, snooze, dueDate, completionDate, recurring, users));
		return "missions";
	}
	
	//button to delete a mission, using the id; returns the user to the mi
	@RequestMapping("/delete-mission-button")
	public String deleteMissionById(long missionId) {
		missionRepo.deleteById(missionId);
		return "missions";
	}
	
	//assigns a mission to a user OR adds a user to mission, works the same; returns the user to the mission page
	@RequestMapping("/assign-mission-button")
	public String assignMissionToUserById(long missionId, long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		mission.addUser(user);
		missionRepo.save(mission);
		return "redirect:/mission?id=" + missionId;
	}

	@RequestMapping("/remove-user-from-mission")
	public String removeUserFromMission(long missionId, long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		mission.removeUser(user);
		missionRepo.save(mission);
		return "redirect:/mission?id=" + missionId;		
	}

}
