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

		User user = new User("Parent", "admin", "contact", "default", "ADMIN", "USER");
		User user2 = new User("Child", "user", "contact2", "default", "USER");
		userRepo.save(user);
		userRepo.save(user2);

		Sector sector = new Sector("bathroom", "/images/sectors/bathroom-1.jpg");
		Sector sector2 = new Sector("kitchen", "/images/sectors/kitchen-1.jpg");
		Sector sector3 = new Sector("Master Bedroom", "/images/sectors/bathroom-1.jpg");
		Sector sector4 = new Sector("Kid1 Bedroom", "/images/sectors/kitchen-1.jpg");
		Sector sector5 = new Sector("dining room", "/images/sectors/bathroom-1.jpg");
		Sector sector6 = new Sector("basement", "/images/sectors/kitchen-1.jpg");
		sectorRepo.save(sector);
		sectorRepo.save(sector2);
		sectorRepo.save(sector3);
		sectorRepo.save(sector4);
		sectorRepo.save(sector5);
		sectorRepo.save(sector6);

		Mission mission = new Mission("MissionName", "description", sector, 1, 2, "2018-12-25", null, false, 0, user);
		Mission mission2 = new Mission("MissionName2", "description2", sector, 4, 3, "2018-11-30", null, false, 0,
				user);
		Mission mission3 = new Mission("MissionName3", "description3", sector2, 21, 4, "2018-12-06", null, false, 0,
				user, user2);
		Mission mission4 = new Mission("MissionName4", "description4", sector3, 14, 4, "2018-12-13", null, false, 0);
		Mission mission5 = new Mission("wash dog", "wash him real good", sector4, 1, 3, "2018-12-12", null, false, 0,
				user2);
		Mission mission6 = new Mission("wash cat", "try it and see", sector5, 1, 4, "2018-12-13", null, false, 0,
				user2);
		Mission mission7 = new Mission("rake leaves", "rake them real good", sector6, 1, 3, "2018-12-01", null, false,
				0, user);
		Mission mission8 = new Mission("dishes", "scrub", sector2, 1, 3, "2018-12-01", null, false, 0, user2);
		Mission mission9 = new Mission("laundry", "wash dry fold put away", sector5, 5, 2, "2018-11-30", null, false, 0,
				user2);
		missionRepo.save(mission);
		missionRepo.save(mission2);
		missionRepo.save(mission3);
		missionRepo.save(mission4);
		missionRepo.save(mission5);
		missionRepo.save(mission6);
		missionRepo.save(mission7);
		missionRepo.save(mission8);
		missionRepo.save(mission9);

	}
}
