package prodapp;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProdPopulator implements CommandLineRunner {

	@Resource
	private UserRepository userRepo;

	@Resource
	private MissionRepository missionRepo;

	@Resource
	private SectorRepository sectorRepo;

	@Override

	public void run(String...args) throws Exception {
		
	User user = new User("admin", "admin", "contact", "ADMIN", "USER");
	User user2 = new User("user", "user", "contact2", "USER");
	userRepo.save(user);
	userRepo.save(user2);
	
	Sector sector = new Sector("bathroom", "/images/sectors/bathroom-1.jpg");
	Sector sector2 = new Sector("kitchen","/images/sectors/kitchen-1.jpg");
	sectorRepo.save(sector2);
	sectorRepo.save(sector);
	
	Mission mission = new Mission("MissionName", "description", sector, 1, 2, "2018-12-25", "", true, 0, user);
	Mission mission2 = new Mission("MissionName2", "description2", sector, 4, 3, "2018-11-30", "", true, 0,
			user);
	Mission mission3 = new Mission("MissionName3", "description3",sector2, 21, 4, "2018-12-06", "", false, 0,
			user, user2);
	Mission mission4 = new Mission("MissionName4", "description4", sector2, 14, 4, "2018-12-13", "", false, 0);
	missionRepo.save(mission);
	missionRepo.save(mission2);
	missionRepo.save(mission3);
	missionRepo.save(mission4);
	

	}
}
