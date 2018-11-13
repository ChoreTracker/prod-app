package prodapp;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SectorController {

	@Resource
	SectorRepository sectorRepo;

	@RequestMapping("/show-sectors")
	public String findAllSectors(Model model) {
		model.addAttribute("sectors", sectorRepo.findAll());
		return "sectors";	
	}
	
	@RequestMapping("/sector")
	public String findOneSector(@RequestParam(value="id") long sectorId, Model model) throws sectorNotFoundException{
		Optional<Sector> sector = sectorRepo.findById(sectorId);
		
		if(sector.isPresent()) {
			model.addAttribute("sectors", sector.get());
			return "sector";
		}
		throw new sectorNotFoundException();
	}

	
	@RequestMapping("/setup-sectors")
	public String addNewSector (Model model) {
	
		return "setup-sectors";
	}
}

