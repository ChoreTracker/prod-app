package prodapp;

import java.util.Collection;
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

	@RequestMapping("/sector")
	public String findOneSector(long sectorId, Model model) throws sectorNotFoundException{
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		
		if(sector.isPresent()) {
			model.addAttribute("sector", sector.get());
			return "sector";
		}
		throw new sectorNotFoundException();
		
	}
	@RequestMapping("/show-sectors")
	public String findAllSectors(Model model) {
		model.addAttribute("sectors", sectorRepo.findAll());
		return "sectors";	
		
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
	@RequestMapping("/remove-sector")
	public String deleteSectorById(Long sectorId) {
		if (sectorRepo.findById(sectorId) != null) {
			sectorRepo.deleteById(sectorId);
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
	
	@RequestMapping("/assign-all-missions")
	public String assignAllMissionsInSectorToUserById(long sectorId, long userId) {
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		Sector sectorResult = sector.get();
		Optional<User> user = userRepo.findById(userId);
		User userResult = user.get();
		for (Mission mission : sectorResult.getMissions()) {
			mission.assignUsers(userResult);
		}
		return "redirect:/sector";
		
	}
	

}
