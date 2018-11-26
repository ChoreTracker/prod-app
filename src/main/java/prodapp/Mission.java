package prodapp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Mission {

	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)
	private long id;

	private String missionName;
	
	@Lob
	private String missionDescription;

	@ManyToMany
	private Collection<User> users;

	@ManyToOne
	private Sector sector;

	private String completionDate;
	private int period;
	private int snooze;
	private String dueDate;
	private boolean recurring;
	private int count;

	public boolean isRecurring() {
		return recurring;
	}

	public long getId() {
		return id;
	}

	public String getMissionName() {
		return missionName;
	}

	public String getMissionDescription() {
		return missionDescription;
	}

	public int getPeriod() {
		return period;
	}

	public int getSnooze() {
		return snooze;
	}

	public String getDueDate() {
		return dueDate;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public Sector getSector() {
		return sector;
	}
	
	
	public long getSectorId() {
		return this.getSector().getId();
	}
	
	public String getSectorImage() {
		return this.getSector().getImageUrl();
	}

	// this method assigns all the users at once, removing any pre-assigned users,
	// if any
	public void assignUsers(User... users) {
		this.users = new HashSet<>(Arrays.asList(users));
	}

	// removes a user from the list of users
	public void removeUser(User user) {
		this.users.remove(user);
	}
	

//	public void removeUsers(Collection<User> users) {
//		this.users.remove(users);
//		
//	}

	
	// adds a user to the list of users
	public void addUser(User user) {
		this.users.add(user);
	}

	// sets the completion date to today
	public void markComplete() {
		LocalDate today = LocalDate.now();
		completionDate = today.toString();
	}

	public void setDueDate(String date) {
		this.dueDate = date;
	}

	// sets the snooze period for a number of days--int
	public void setSnoozePeriod(int days) {
		this.snooze = days;
	}

	// adds the snooze period to the due date
	public void hitSnooze() {
		LocalDate dateDue = LocalDate.parse(dueDate);
		dateDue = dateDue.plusDays(snooze);
		this.dueDate = dateDue.toString();
	}

	public void setPeriod(int periodDays) {
		this.period = periodDays;	
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public void increaseCount(int i) {
		this.count ++;
	}
	
	public void assignUsers(Collection<User> usersList) {
		this.users = usersList;
	}
	
	public void resetCount() {
		this.count = 0;
	}

	public Mission(String missionName, String missionDescription, int period, int snooze, String dueDate,
			String completionDate, boolean recurring, int count, User...users) {
		this.missionName = missionName;
		this.missionDescription = missionDescription;
		this.users = new HashSet<>(Arrays.asList(users));
		this.period = period;
		this.snooze = snooze;
		this.dueDate = dueDate;
		this.completionDate = completionDate;
		this.recurring = recurring;
		this.setCount(count);
	}

	public Mission() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mission other = (Mission) obj;
		if (id != other.id)
			return false;
		return true;
	}
  
}
