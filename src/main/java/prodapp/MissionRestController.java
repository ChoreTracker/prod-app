package prodapp;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missions")
public class MissionRestController {

	@Resource
	private MissionRepository missionRepo;

	@Resource
	private UserRepository userRepo;

	@Resource
	private SectorRepository sectorRepo;

	@RequestMapping("")
	public Iterable<Mission> findAllMissions() {
		return missionRepo.findAll();
	}

	@RequestMapping("/{id}")
	public Optional<Mission> findOneMission(@PathVariable long missionId) {
		return missionRepo.findById(missionId);
	}

	@RequestMapping("/users/{userId}")
	public Collection<Mission> findAllMissionsByUser(@PathVariable long userId) {
		Optional<User> userResult = userRepo.findById(userId);
		User user = userResult.get();
		return missionRepo.findByUsersContains(user);
	}
	
	@RequestMapping("/sectors/{sectorId}")
	public Collection<Mission> findAllMissionsInSector(@PathVariable long sectorId){
		return missionRepo.findAllBySectorAndCompletionDateNotNullOrderByDueDate(sectorId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
