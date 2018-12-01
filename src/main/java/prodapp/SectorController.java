package prodapp;

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

	@RequestMapping("/show-sectors")
	public String findAllSectors(Model model) {
		model.addAttribute("sectors", sectorRepo.findAll());
		return "sectors";
	}

	@RequestMapping("/sector")
	public String findOneSector(@RequestParam(value = "id") long sectorId, Model model) throws sectorNotFoundException {
		Optional<Sector> sector = sectorRepo.findById(sectorId);

		if (sector.isPresent()) {
			Sector sectorResult = sector.get();
			model.addAttribute("sector", sectorResult);
			model.addAttribute("users", userRepo.findAll());
			model.addAttribute("missions", missionRepo.findAllBySector(sectorResult));
			return "sector";
		}
		throw new sectorNotFoundException();

	}

	@RequestMapping("/add-sector-button")
	public String addNewSector(@RequestParam String sectorName, @RequestParam String imageUrl) {
		Sector sector = sectorRepo.findBySectorName(sectorName);
		if (sector == null) {
			imageUrl = "/images/sectors/" + imageUrl;
			sector = new Sector(sectorName, imageUrl);
			sectorRepo.save(sector);
		}
		return "redirect:/show-sectors";
	}

	@RequestMapping("/missionDone-sector-button")
	public String setAsComplete(@RequestParam long missionId, @RequestParam long sectorId) {
		Optional<Mission> result = missionRepo.findById(missionId);
		Mission mission = result.get();
		mission.markComplete();
		missionRepo.save(mission);

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
	@RequestMapping("/admin/remove-sector")
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
			int snooze, String dueDate, boolean recurring, @RequestParam(value = "users", required=false) long[] users) {
		Optional<Sector> sectorResult = sectorRepo.findById(sectorId);
		Sector sector = sectorResult.get();
		Mission newMission = new Mission(missionName, missionDescription, sector, period, snooze, dueDate, null,
				recurring, 0);
		missionRepo.save(newMission);
		if (users != null) {
			for (int i = 0; i < users.length; i++) {
				Optional<User> userResult = userRepo.findById(users[i]);
				if (userResult.isPresent()) {
					User user = userResult.get();
					newMission.addUser(user);
				}
			}
		}
		missionRepo.save(newMission);

		return "redirect:/sector?id=" + sectorId;
	}
}
