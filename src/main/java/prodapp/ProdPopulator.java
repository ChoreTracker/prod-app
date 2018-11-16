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
		
	User user = new User("Name", "contact");
	User user2 = new User("Name2", "contact2");
	userRepo.save(user);
	userRepo.save(user2);
	
	Mission mission = new Mission("MissionName", "description", "period", 0, "dueDate", "completionDate", true, user);
	Mission mission2 = new Mission("MissionName2", "description2", "period2", 0, "dueDate2", "completionDate2", true,
			user);
	Mission mission3 = new Mission("MissionName3", "description3", "period3", 0, "dueDate3", "completionDate3", false,
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
