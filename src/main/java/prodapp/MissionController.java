package prodapp;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MissionController {

	@Resource
	MissionRepository missionRepo;

	@Resource
	UserRepository userRepo;

	// all info on one mission is available; returns the single mission page
	@RequestMapping("/mission")
	public String findOneMission(@RequestParam(value = "id") long missionId, Model model)
			throws missionNotFoundException {
		Optional<Mission> mission = missionRepo.findById(missionId);
		if (mission.isPresent()) {
			model.addAttribute("mission", mission.get());
			return "mission";
		}
		throw new missionNotFoundException();
	}

	// shows all the missions, regardless of sector; returns the all missions page
	@RequestMapping("/show-missions")
	public String findAllMissions(Model model) {
		model.addAttribute("missions", missionRepo.findAll());
		return "missions";

	}

	// button to create a mission, doesn't add it to a
	// sector--("/add-mission-to-sector-button") does that; returns the all missions
	// page
	@RequestMapping("/create-mission-button")
	public String createMission(String missionName, String missionDescription, int period, int snooze, String dueDate,
			String completionDate, boolean recurring, int count, User...users) {
		missionRepo.save(new Mission(missionName, missionDescription, period, snooze, dueDate, completionDate, recurring, 0, users));
		return "missions";
	}

	// button to delete a mission, using the id; returns the user to the mi
	@RequestMapping("/admin/delete-mission-button")
	public String deleteMissionById(long missionId) {
		missionRepo.deleteById(missionId);
		return "missions";
	}

	// assigns a mission to a user OR adds a user to mission, works the same;
	// returns the user to the mission page
	@RequestMapping("/admin/assign-mission-button")
	public String assignMissionToUserById(long missionId, long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		mission.addUser(user);
		missionRepo.save(mission);
		return "redirect:/mission?id=" + missionId;
	}

	@RequestMapping("/admin/remove-user-from-mission")
	public String removeUserFromMission(long missionId, long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		mission.removeUser(user);
		missionRepo.save(mission);
		return "redirect:/mission?id=" + missionId;

	}
	
	public String findAllCompletedMissionsByUser(Model model,long id) {
		Collection<Mission> foundMissions = missionRepo.findCompletionDateNotNullByUsersIdOrderByDueDate(id);
		model.addAttribute("missions", foundMissions);
		return "missions";
		
	}

//	@RequestMapping("/show-unassigned-missions")
//	public String findUnassignedMissions(Model model) {
//		Collection<Mission> unassignedMissions = new HashSet<>();
//		for (Mission mission : missionRepo.findAll()) {
//			if (mission.getUsers().size() == 0 || mission.getUsers() == null) {
//				unassignedMissions.add(mission);
//			}
//		}
//		model.addAttribute("missions", unassignedMissions);
//		return "missions";
//	}
	
	@RequestMapping("/show-unassigned-missions")
	public String showAllUnassignedMissions(Model model) {
		Collection<Mission> unassignedMissions = missionRepo.findAllByUsersIsNullAndRecurringIsFalse();
		model.addAttribute("missions", unassignedMissions);
		return "missions";
	}

	// pass in the mission id, sets the completion date to current date
	@RequestMapping("/mission-complete-button")
	public String setAsComplete(long missionId, long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.markComplete();
		missionRepo.save(mission);
		return "redirect:/user?id=" + userId;
	}
	
	@RequestMapping(path="/missions/{missionId}/done", method = RequestMethod.POST)
	public String markComplete(@PathVariable long missionId, @RequestParam long userId, Model model) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.markComplete();
		missionRepo.save(mission);
		Optional<User> user = userRepo.findById(userId);
		
		model.addAttribute("user", user.get());
		return "partials/mission-complete";
	}

	public void createDueDate(long missionId, String date) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.setDueDate(date);
		missionRepo.save(mission);
	}

	public void setSnooze(long missionId, int days) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.setSnoozePeriod(1);
		missionRepo.save(mission);

	}
	@RequestMapping("/snooze-button")
	public String snoozeMission(long missionId, long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.hitSnooze();
		return "redirect:/user?id=" + userId;
	}

	public void setMissionPeriod(long missionId, int periodDays) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.setPeriod(periodDays);

	}

	public String findMissionsByDueDate(Model model, String dateString) {
		Collection<Mission> missionsDue = new HashSet<>();
		for (Mission mission : missionRepo.findAll()) {
			if (mission.getDueDate().equals(dateString)) {
				missionsDue.add(mission);
			}
		}
		model.addAttribute("missions", missionsDue);
		return "missions";
	}

	public String findMissionsDueToday(Model model) {
		LocalDate today = LocalDate.now();
		Collection<Mission> missionsDue = new HashSet<>();
		for (Mission mission : missionRepo.findAll()) {
			if (mission.getDueDate().equals(today.toString())) {
				missionsDue.add(mission);
			}
		}
		model.addAttribute("missions", missionsDue);
		return "missions";
	}

	public String findMissionsDueTodayForUser(Model model, long userId) {
		LocalDate today = LocalDate.now();
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		Collection<Mission> missionsDue = new HashSet<>();
		for (Mission mission : missionRepo.findAll()) {
			if(mission.getDueDate().equals(today.toString()) &&  mission.getCompletionDate().equals("") && mission.getUsers().contains(user)) {
				missionsDue.add(mission);
			}
		}
		model.addAttribute("missions", missionsDue);
		return "missions";

	}

	public String findMissionsDueThisWeekForUser(Model model, long userId) {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalDate todayPlusEightDays = today.plusDays(8);
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		Collection<Mission> missionsDue = new HashSet<>();
		for (Mission mission : missionRepo.findAll()) {
			if(mission.getUsers().contains(user) && 
					mission.getCompletionDate().equals("") &&
					LocalDate.parse(mission.getDueDate()).isBefore(todayPlusEightDays) && 
					LocalDate.parse(mission.getDueDate()).isAfter(yesterday)) {
				missionsDue.add(mission);
			}
		}
		model.addAttribute("missions", missionsDue);
		return "missions";
	}

	public String findIncompleteMissionsDueThisWeekForUser(Model model, long userId) {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalDate todayPlusEightDays = today.plusDays(8);
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		Collection<Mission> missionsDue = new HashSet<>();
		for (Mission mission : missionRepo.findAll()) {
			if(mission.getUsers().contains(user) && 
					mission.getCompletionDate().equals("") &&
					LocalDate.parse(mission.getDueDate()).isBefore(todayPlusEightDays) && 
					LocalDate.parse(mission.getDueDate()).isAfter(yesterday)) {
				missionsDue.add(mission);
			}
		}
		model.addAttribute("missions", missionsDue);
		return "missions";
	}
	@RequestMapping("/default-user-missions")
	public String sortUserIncompleteMissionsByDueDate(Model model) {
		User loggedInUser = findLoggedInUser();

		Collection<Mission> foundMissions = missionRepo.findAllByUsersAndCompletionDateAndRecurringIsFalseOrderByDueDate(loggedInUser, "");
		model.addAttribute("missions", foundMissions);
		return "missions";
	}
	
	public String sortUserMissionsByDueDate(Model model) {
		User loggedInUser = findLoggedInUser();

	Collection<Mission> foundMissions = missionRepo.findAllByUsersAndRecurringIsFalseOrderByDueDate(loggedInUser);
		model.addAttribute("missions", foundMissions);
		return "missions";
	}

	private User findLoggedInUser() {
		Object activeUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User loggedInUser = User.class.cast(activeUser);
		return loggedInUser;
	}
	@RequestMapping("/claim-mission-button")
	public void claimUnassignedMission(long missionId, User user) {
		User activeUser = findLoggedInUser();
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.addUser(activeUser);
		missionRepo.save(mission);
	}
	@RequestMapping("/claim-mission-assigned-button")
	public void claimAssignedMission(long missionId, User user) {
		User activeUser = findLoggedInUser();
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.removeUsers(mission.getUsers());
		mission.addUser(activeUser);
		missionRepo.save(mission);
	}

	@RequestMapping("/make-recurring")
	public void makeMissionRecurring(long missionId) {
		Optional<Mission> missionChosen = missionRepo.findById(missionId);
		Mission mission = missionChosen.get();
		Mission newMission = new Mission(mission.getMissionName(), mission.getMissionDescription(),
				mission.getPeriod(), mission.getSnooze(), "", "", true, 0);
		newMission.assignUsers(mission.getUsers());
		missionRepo.save(newMission);
	}
}
