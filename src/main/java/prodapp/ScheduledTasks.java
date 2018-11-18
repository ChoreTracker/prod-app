package prodapp;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.assertj.core.util.Arrays;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	@Resource
	MissionRepository missionRepo;

//	int counter = 0;
//
//    @Scheduled(fixedRate = 10000)
//    public void reportCurrentTime() {
//    	
//       System.out.println("This is the count:" + counter);
//       counter ++;
//    }
    
    //runs at 3 am every day
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
	
	
}
