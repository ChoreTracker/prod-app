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
	
	@Resource
	private RewardRepository rewardRepo;

	@Override


	public void run(String...args) throws Exception {
		
	User user = new User("Mom", "admin", "contact", "default", 5, "ADMIN", "USER");
	User user2 = new User("Dad", "admin", "contact2", "default", 5, "ADMIN", "USER");
	User user3 = new User("Emma", "user", "contact2", "default", 5, "USER");
	User user4 = new User("Lucy", "user", "contact2", "default", 15, "USER");
	User user5 = new User("Sophie", "user", "contact2", "default", 2,"USER");
	User user6 = new User("Jackson", "user", "contact2", "default", 0, "USER");
	userRepo.save(user);
	userRepo.save(user2);
	userRepo.save(user3);
	userRepo.save(user4);
	userRepo.save(user5);
	userRepo.save(user6);
	
	Sector sector = new Sector("Bathroom", "/images/icons/bathtub.svg");
	Sector sector2 = new Sector("Kitchen","/images/sectors/kitchen-1.jpg");
	Sector sector3 = new Sector("Master Bedroom", "/images/sectors/bedroom-6.jpg");
	Sector sector4 = new Sector("Girls' Bedroom","/images/sectors/bedroom-3.jpg");
	Sector sector5= new Sector("Dining Room", "/images/sectors/dining-rm-1.jpg");
	Sector sector6 = new Sector("Garage","/images/sectors/garage-1.jpg");
	Sector sector7 = new Sector("Yard", "/images/sectors/yard-2.jpg");
	Sector sector8 = new Sector("Playroom", "/images/sectors/play-rm-2.jpg");
	Sector sector9 = new Sector("Jackson's Bedroom", "/images/sectors/bedroom-5.jpg");
	Sector sector10 = new Sector("Laundry Room", "/images/sectors/laundry-rm-2.jpg");
	sectorRepo.save(sector);
	sectorRepo.save(sector2);
	sectorRepo.save(sector3);
	sectorRepo.save(sector4);
	sectorRepo.save(sector5);
	sectorRepo.save(sector6);
	sectorRepo.save(sector7);
	sectorRepo.save(sector8);
	sectorRepo.save(sector9);
	sectorRepo.save(sector10);
	
	Mission mission = new Mission("Clean Toilet", "scrub the toilet", sector, 1, 2, "2018-12-22", null, true, 0, 1, user);
	Mission mission2 = new Mission("Scrub Tub", "clean the tub", sector, 4, 3, "2018-12-30", null, true, 0, 2,
			user);
	Mission mission3 = new Mission("Sweep the Floor", "sweep thoroughly", sector2, 21, 4, "2018-12-14", null, false, 0, 1,
			user, user2);
	Mission mission4 = new Mission("Make the Bed", "no tuck", sector3, 14, 4, "2018-12-14", null, false, 0, 0);
	Mission mission5 = new Mission("wash dog", "wash him real good", sector6, 1,3, "2018-12-14", null, false, 0, 2, user2);
    Mission mission6 = new Mission("wash cat", "try it and see", sector, 1,4, "2018-12-17", null, false, 0, 2, user5);
    Mission mission7 = new Mission("rake leaves", "rake them real good", sector7, 1,3, "2018-12-16", null, true, 0, 3, user);
    Mission mission8 = new Mission("dishes", "scrub", sector2, 1,3, "2018-12-14", null, true, 0, 0, user2);
    Mission mission13 = new Mission("take out the trash", "to the curb", sector2, 7, 1, "2018-12-15", null, true, 0, 0);
    Mission mission14 = new Mission("grocery shopping", "all the things", sector2, 5, 3, "2018-12-15", null, true, 0, 2, user2);
    Mission mission15 = new Mission("clean the fridge", "throw out the leftovers", sector2, 14, 3, "2018-12-15", null, true, 0, 2, user2);
    Mission mission9 = new Mission("laundry", "wash dry fold put away", sector10, 5,2, "2018-12-16", null, true, 0, 2, user2);
    Mission mission10 = new Mission("Put away legos", "in the bin please", sector8, 14, 4, "2018-12-16", null, false, 0, 0, user4);
    Mission mission11 = new Mission("Vacuum", "the whole room, including the corners", sector8, 14, 4, "2018-12-16", null, false, 0, 1, user3);
    Mission mission12 = new Mission("Make the Bed", "for real", sector4, 1, 1, "2018-12-14", null, false, 0, 0, user3, user4, user5);
    Mission mission16 = new Mission("Vacuum", "the whole room", sector4, 7, 4, "2018-12-14", null, false, 0, 1, user3, user4, user5);
    Mission mission17 = new Mission("Pick up toys", "pick up and put them away properly", sector4, 3, 1, "2018-12-16", null, false, 0, 0, user3, user4, user5);
    Mission mission18 = new Mission("Unload the Dishwasher", "put away everything properly", sector2, 14, 4, "2018-12-14", null, false, 0, 1, user3, user4, user5);
    Mission mission19 = new Mission("Vacuum", "the whole room, including the corners", sector8, 14, 4, "2018-12-16", null, false, 0, 1, user3);
    Mission mission20 = new Mission("Make the Bed", "for real", sector9, 1, 1, "2018-12-14", null, false, 0, 0, user6);
    Mission mission21 = new Mission("Vacuum", "the whole room", sector9, 7, 4, "2018-12-14", null, false, 0, 1, user6);
    Mission mission22 = new Mission("Pick up toys", "pick up and put them away properly", sector9, 3, 1, "2018-12-16", null, false, 0, 1, user6);
    missionRepo.save(mission);
	missionRepo.save(mission2);
	missionRepo.save(mission3);
	missionRepo.save(mission4);
	missionRepo.save(mission5);
    missionRepo.save(mission6);
    missionRepo.save(mission7);
    missionRepo.save(mission8);
    missionRepo.save(mission9);
    missionRepo.save(mission10);
    missionRepo.save(mission11);
    missionRepo.save(mission12);
    missionRepo.save(mission13);
    missionRepo.save(mission14);
    missionRepo.save(mission15);
    missionRepo.save(mission16);
    missionRepo.save(mission17);
    missionRepo.save(mission18);
    missionRepo.save(mission19);
    missionRepo.save(mission20);
    missionRepo.save(mission21);
    missionRepo.save(mission22);
   
    Reward reward1 = new Reward("Cash", "$5", 20, false);
    Reward reward2 = new Reward("Dessert", "Special Dessert", 10, false);
    Reward reward3 = new Reward("Video Game Time", "2 hours of extra video game time", 10, false);
    Reward reward4 = new Reward("Pass Mission", "Give one of your missions to someone else", 15, false);
    Reward reward5 = new Reward("Pick the Movie", "Get to pick the movie to watch for movie night", 10, false);
    Reward reward6 = new Reward("Date Night", "Take your lovely spouse out on a date night", 40, false);
    rewardRepo.save(reward1);
    rewardRepo.save(reward2);
    rewardRepo.save(reward3);
    rewardRepo.save(reward4);
    rewardRepo.save(reward5);
    rewardRepo.save(reward6);
	}
}
