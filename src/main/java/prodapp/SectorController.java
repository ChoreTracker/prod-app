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
			model.addAttribute("sector", sector.get());
//			model.addAttribute("sector", missionRepo.findAllBySectorAndCompletionDateNotNullOrderByDueDate(sectorId));
			return "sector";
		} // this will also show all the missions in the sector through the
			// sector.getMissions();
		throw new sectorNotFoundException();

	}

	@RequestMapping(path = "/admin/sectors/add/{sectorName}", method = RequestMethod.POST)
	public String addSector(@PathVariable String sectorName, Model model) {
		Sector sector = sectorRepo.findBySectorName(sectorName);
		if (sector == null) {
			sector = new Sector(sectorName);
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
	@RequestMapping("/admin/assign-all-missions-button")
	public String assignAllMissionsInSectorToUserById(long sectorId, long userId) {
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		Sector sectorResult = sector.get();
		Optional<User> user = userRepo.findById(userId);
		User userResult = user.get();
		for (Mission mission : sectorResult.getMissions()) {
			mission.assignUsers(userResult);
			missionRepo.save(mission);
		}
		return "redirect:/sector?id=" + sectorId;

	}

	// button to add a mission to a sector, using the ids of both, say from a view
	// of the sector
	@RequestMapping("/add-mission-to-sector-button")
	public String addMissionToSector(long sectorId, long missionId) {
		Optional<Sector> result = sectorRepo.findById(sectorId);
		if (result.isPresent()) {
			Sector sector = result.get();
			Optional<Mission> missionToAdd = missionRepo.findById(missionId);
			if (missionToAdd.isPresent()) {
				Mission mission = missionToAdd.get();
				sector.addMission(mission);
				sectorRepo.save(sector);
				return "redirect:/sector?id=" + sectorId;
			}

		}
		return null;
	}

	@RequestMapping("/setup-sectors")
	public String showAllSectors(Model model) {

		return "setup-sectors";
	}

	@RequestMapping("/make-mission-within-sector")
	public String createMissionInSector(long sectorId, String missionName, String missionDescription, int period,
			int snooze, String dueDate, String completionDate, boolean recurring, int count, User... users) {
		Mission newMission = new Mission(missionName, missionDescription, period, snooze, dueDate, completionDate,
				recurring, count, users);
		missionRepo.save(newMission);
		long missionId = newMission.getId();
		addMissionToSector(sectorId, missionId);
		return "redirect:/sector?id=" + sectorId;
	}
}
