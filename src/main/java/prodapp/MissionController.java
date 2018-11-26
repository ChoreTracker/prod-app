package prodapp;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MissionController {

	@Resource
	MissionRepository missionRepo;

	@Resource
	UserRepository userRepo;
	
	@Resource
	SectorRepository sectorRepo;

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

	//This button is in use!
	@RequestMapping("/create-mission-button")
	public String createMission(String missionName, String missionDescription, int period, int snooze, String dueDate, boolean recurring, @RequestParam long sectorId, @RequestParam long userId) {
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		Mission newMission = new Mission(missionName, missionDescription, period, snooze, dueDate, "", recurring, 0, user);
		missionRepo.save(newMission);
		long missionId = newMission.getId();
		putMissionInSector(sectorId, missionId);
		return "redirect:/user?id=" + userId;
	}
	
	//this method is in use also!
	public void putMissionInSector(long sectorId, long missionId) {
		Optional<Sector> result = sectorRepo.findById(sectorId);
		if (result.isPresent()) {
			Sector sector = result.get();
			Optional<Mission> missionToAdd = missionRepo.findById(missionId);
			if (missionToAdd.isPresent()) {
				Mission mission = missionToAdd.get();
				sector.addMission(mission);
				sectorRepo.save(sector);
			}
		}
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
	public String setAsComplete(@RequestParam long missionId, @RequestParam  long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.markComplete();
		missionRepo.save(mission);
		
		return "redirect:/user?id=" + userId;
	}
	
	@RequestMapping("/snooze-mission")
	public String snoozeMission(@RequestParam long missionId, @RequestParam (value="id") long userId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.hitSnooze();
		missionRepo.save(mission);
		return "redirect:/user?id=" + userId;
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
	public String claimUnassignedMission(@RequestParam long missionId, @RequestParam (value="id") long userId) {
		User activeUser = findLoggedInUser();
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.addUser(activeUser);
		missionRepo.save(mission);
		return "redirect:/user?id=" + userId;
	}
	
	@RequestMapping("/claim-mission-assigned-button")
	public void claimAssignedMission(long missionId) {
		User activeUser = findLoggedInUser();
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.assignUsers(activeUser);
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
