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
	
	Mission mission = new Mission("MissionName", "description", 3, 0, "dueDate", "completionDate", true, 0, user);
	Mission mission2 = new Mission("MissionName2", "description2", 4, 0, "dueDate2", "completionDate2", true, 0,
			user);
	Mission mission3 = new Mission("MissionName3", "description3", 0, 0, "dueDate3", "completionDate3", false, 0,
			user, user2);
	missionRepo.save(mission);
	missionRepo.save(mission2);
	missionRepo.save(mission3);
	
	Sector sector = new Sector("sectorName", mission, mission2);
	Sector sector2 = new Sector("sectorName2", mission3);
	sectorRepo.save(sector2);
	sectorRepo.save(sector);

	}
}
