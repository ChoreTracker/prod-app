package prodapp;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Resource
	MissionRepository missionRepo;

	// runs at 3 am every day
	@Scheduled(cron = "0 0 3 * * *")
	public void generateRecurringTasks() {
		for (Mission mission: missionRepo.findByRecurring(true)) {
			mission.increaseCount(1);
			if (mission.getCount()== mission.getPeriod()) {
				LocalDate localDueDate = LocalDate.now().plusDays(mission.getPeriod());
				String newDueDate = localDueDate.toString();
				Mission newMission = new Mission(mission.getMissionName(), mission.getMissionDescription(),
						mission.getPeriod(), mission.getSnooze(),
						newDueDate, "", false, 0);
				newMission.assignUsers(mission.getUsers());
				missionRepo.save(newMission);
				mission.resetCount();
				missionRepo.save(mission);
			}
		}
	}
	//ten minutes in milliseconds, just for the demo
//	@Scheduled(fixedRate = 600000)
//	public void generateDemoRecurringTasks() {
//		for (Mission mission : missionRepo.findByRecurring(true)) {
//			mission.increaseCount(1);
//			if (mission.getCount() == mission.getPeriod()) {
//				LocalDate localDueDate = LocalDate.now().plusDays(mission.getPeriod());
//				String newDueDate = localDueDate.toString();
//				Mission newMission = new Mission(mission.getMissionName(), mission.getMissionDescription(),
//						mission.getPeriod(), mission.getSnooze(), newDueDate, "", false, 0);
//				newMission.assignUsers(mission.getUsers());
//				missionRepo.save(newMission);
//				mission.resetCount();
//				missionRepo.save(mission);
//			}
//		}
//	}

}
