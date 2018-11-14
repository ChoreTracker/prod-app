package prodapp;


import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String findOneSector(long sectorId, Model model) throws sectorNotFoundException{
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		
		if(sector.isPresent()) {
			model.addAttribute("sector", sector.get());
			return "sector";
		} //this will also show all the missions in the sector through the sector.getMissions();
		throw new sectorNotFoundException();
<<<<<<< HEAD
		
	}
	
		
	@RequestMapping("/add-sector")
	public String addSector(String sectorName, Mission...missions) {
		Sector sector = sectorRepo.findBySectorName(sectorName);
		if (sector == null) {
			sector = new Sector(sectorName);
			sectorRepo.save(sector);
		}
		return "redirect:/show-sectors";
		

	}
	//deletes a sector and also all the missions in the sector
	@RequestMapping("/remove-sector")
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
	
	//button that assigns all the missions in a sector to one user, using the ids of both
	@RequestMapping("/assign-all-missions-button")
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
		
=======
>>>>>>> d5083bc7b4dbdc2b53c5645400c9b05e6224ee40
	}
	//button to add a mission to a sector, using the ids of both, say from a view of the sector
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
	public String addNewSector (Model model) {
	
		return "setup-sectors";
	}
}

