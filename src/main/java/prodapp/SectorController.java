package prodapp;

import java.security.Principal;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SectorController {

	@Resource
	SectorRepository sectorRepo;

	@Resource
	MissionRepository missionRepo;

	@Resource
	UserRepository userRepo;
	
	@Resource
	RewardRepository rewardsRepo;

	@RequestMapping("/show-sectors")
	public String findAllSectors(Model model, Principal principal) {
		String activeUser = principal.getName();
		Optional<User> userTheme = userRepo.findByUserName(activeUser);
		User loggedInUser = userTheme.get();
		model.addAttribute("loggedInUser", loggedInUser);
		
		model.addAttribute("sectors", sectorRepo.findAll());

		model.addAttribute("rewards", rewardsRepo.findAll());

		return "sectors";
	}

	@RequestMapping("/sector")
	public String findOneSector(@RequestParam(value = "id") long sectorId, Model model, Principal principal) throws sectorNotFoundException {
		Optional<Sector> sector = sectorRepo.findById(sectorId);

		if (sector.isPresent()) {
			Sector sectorResult = sector.get();

			model.addAttribute("rewards", rewardsRepo.findAll());

			String activeUser = principal.getName();
			Optional<User> userTheme = userRepo.findByUserName(activeUser);
			User loggedInUser = userTheme.get();
			model.addAttribute("loggedInUser", loggedInUser);
			model.addAttribute("sector", sectorResult);
			model.addAttribute("users", userRepo.findAll());
			model.addAttribute("missions", missionRepo.findAllBySectorAndRecurringIsFalse(sectorResult));
			return "sector";
		}
		throw new sectorNotFoundException();

	}

// use on the sectors page
	@RequestMapping("/add-sector-button")
	public String addNewSector(@RequestParam String sectorName, @RequestParam String imageUrl) {
		Sector sector = sectorRepo.findBySectorName(sectorName);
		if (sector == null) {
			imageUrl = "/images/icons/" + imageUrl;
			sector = new Sector(sectorName, imageUrl);
			sectorRepo.save(sector);
		}
		return "redirect:/show-sectors";
	}

	// use on the user page
	@RequestMapping("/add-sector-button-from-user")
	public String addNewSectorOnUser(@RequestParam String sectorName, @RequestParam String imageUrl,
			@RequestParam long userId) {
		Sector sector = sectorRepo.findBySectorName(sectorName);
		if (sector == null) {
			imageUrl = "/images/sectors/" + imageUrl;
			sector = new Sector(sectorName, imageUrl);
			sectorRepo.save(sector);
		}
		return "redirect:/user?id=" + userId;
	}

	@RequestMapping("/missionDone-sector-button")
	public String setAsComplete(@RequestParam long missionId, @RequestParam long sectorId, Principal principal) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.markComplete();
		missionRepo.save(mission);
		String activeUser = principal.getName().toString();
		Optional<User> loggedInUser = userRepo.findByUserName(activeUser);
		long userId = loggedInUser.get().getId();
		Optional<User>userResult = userRepo.findById(userId);
		User user = userResult.get();
		int reward = mission.getRewardValue();
		user.setRewardBalance(user.getRewardBalance() + reward);
		userRepo.save(user);
		return "redirect:/sector?id=" + sectorId;
	}

	@RequestMapping("/snooze-mission-sector")
	public String snoozeMission(@RequestParam long missionId, @RequestParam long sectorId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.hitSnooze();
		missionRepo.save(mission);
		return "redirect:/sector?id=" + sectorId;
	}

	@RequestMapping(path = "/admin/sectors/add/{sectorName}", method = RequestMethod.POST)
	public String addSector(@PathVariable String sectorName, Model model) {
		Sector sector = sectorRepo.findBySectorName(sectorName);
		if (sector == null) {
			sector = new Sector(sectorName, "");
			sectorRepo.save(sector);
		}
		model.addAttribute("sectors", sectorRepo.findAll());
		return "redirect:/show-sectors";

	}
	// Mission...missions after sectorName
//	@RequestMapping(path = "/sectors/remove/{sectorName}", method = RequestMethod.POST)
//	public String removeSector(@PathVariable String sectorName, Model model) {
//		Sector sector = sectorRepo.findBySectorName(sectorName);
//		if (sector != null) {
//			
//			sectorRepo.delete(sector);
//		}
//		model.addAttribute("sectors", sectorRepo.findAll());
//		return "redirect:/show-sectors";
//		
//
//	}

	// deletes a sector and also all the missions in the sector
	@RequestMapping("/admin/remove-sector-button")
	public String deleteSectorById(Long sectorId) {
		if (sectorRepo.findById(sectorId) != null) {
			sectorRepo.deleteById(sectorId);
			missionRepo.deleteBySector(sectorId);
		}
		return "redirect:/show-sectors";
	}

	@RequestMapping("/sector-missions")
	public String findMissionsBySectorId(long sectorId, Model model) {
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		Sector sectorResult = sector.get();
		model.addAttribute("sector", sectorResult);
		model.addAttribute("missions", sectorResult.getMissions());
		return "sector";
	}

	// button that assigns all the missions in a sector to one user, using the ids
	// of both
	@RequestMapping("/assign-all-missions-button")
	public String assignAllMissionsInSectorToUserById(long sectorId, long userId) {
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		Sector sectorResult = sector.get();
		Optional<User> user = userRepo.findById(userId);
		User userResult = user.get();
		for (Mission mission : missionRepo.findAllBySector(sectorResult)) {
			mission.assignUsers(userResult);
			missionRepo.save(mission);
		}
		sectorRepo.save(sectorResult);
		return "redirect:/sector?id=" + sectorId;

	}

	// button to add a mission to a sector, using the ids of both, say from a view
	// of the sector
//	@RequestMapping("/add-mission-to-sector-button")
//	public String addMissionToSector(long sectorId, long missionId) {
//		Optional<Sector> result = sectorRepo.findById(sectorId);
//		if (result.isPresent()) {
//			Sector sector = result.get();
//			Optional<Mission> missionToAdd = missionRepo.findById(missionId);
//			if (missionToAdd.isPresent()) {
//				Mission mission = missionToAdd.get();
//				sector.addMission(mission);
//				sectorRepo.save(sector);
//				return "redirect:/sector?id=" + sectorId;
//			}
//
//		}
//		return null;
//	}
//	

	@RequestMapping("/setup-sectors")
	public String showAllSectors(Model model) {

		return "setup-sectors";
	}

	@RequestMapping("/make-mission-within-sector")
	public String createMissionInSector(long sectorId, String missionName, String missionDescription, int period,
			int snooze, String dueDate, boolean recurring, int rewardValue,
			@RequestParam(value = "users", required = false) long[] users) {
		Optional<Sector> sectorResult = sectorRepo.findById(sectorId);

		Sector sector = sectorResult.get();
		Mission newMission = new Mission(missionName, missionDescription, sector, period, snooze, dueDate, null, false,
				0, rewardValue);
		missionRepo.save(newMission);
		if (users != null) {
			for (int i = 0; i < users.length; i++) {
				Optional<User> userResult = userRepo.findById(users[i]);
				if (userResult.isPresent()) {
					User user = userResult.get();
					newMission.addUser(user);
				}
			}
			missionRepo.save(newMission);
		}

		if (recurring) {
			Mission newRecurring = new Mission(missionName, missionDescription, sector, period, snooze, dueDate, null,
					true, 0, rewardValue);
			missionRepo.save(newRecurring);
			if (users != null) {
				for (int i = 0; i < users.length; i++) {
					Optional<User> userResult = userRepo.findById(users[i]);
					if (userResult.isPresent()) {
						User user = userResult.get();
						newRecurring.addUser(user);
					}
				}
			}
			missionRepo.save(newRecurring);
		}
		return "redirect:/sector?id=" + sectorId;
	}
}
