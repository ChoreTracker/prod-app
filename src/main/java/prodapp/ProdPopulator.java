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
	public void run(String... args) throws Exception {

		User user = new User("Name", "contact");
		User user2 = new User("Name2", "contact2");
		user = userRepo.save(user);
		user2 = userRepo.save(user2);

		Mission mission = new Mission("MissionName", "description", "period", "snooze", "dueDate", "completionDate",
				true, user);
		Mission mission2 = new Mission("MissionName2", "description2", "period2", "snooze2", "dueDate2",
				"completionDate2", true, user);
		Mission mission3 = new Mission("MissionName3", "description3", "period3", "snooze3", "dueDate3",
				"completionDate3", false, user, user2);
		mission = missionRepo.save(mission);
		mission2 = missionRepo.save(mission2);
		mission3 = missionRepo.save(mission3);

		Sector sector = new Sector("sectorName", mission, mission2);
		Sector sector2 = new Sector("sectorName2", mission3);
		sector2 = sectorRepo.save(sector2);
		sector = sectorRepo.save(sector);
	}
}
