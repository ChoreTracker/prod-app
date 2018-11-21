package prodapp;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sectors")
public class SectorRestController {

	@Resource
	private MissionRepository missionRepo;
	
	@Resource
	private UserRepository userRepo;
	
	@Resource
	private SectorRepository sectorRepo;
	
	@RequestMapping("")
	public Iterable<Sector> findAllSectors(){
		return sectorRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public Optional<Sector> findOneSector(@PathVariable long userId){
		return sectorRepo.findById(userId);
	}
	
	@RequestMapping("/{id}/missions")
	public Collection<Mission> findMissionsBySector(@PathVariable long sectorId) {
		return missionRepo.findAllBySectorAndCompletionDateNotNullOrderByDueDate(sectorId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
