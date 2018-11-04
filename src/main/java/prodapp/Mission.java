package prodapp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Mission {

	@Id
	@GeneratedValue
	private long id;

	private String missionName;
	private String missionDescription;
	
	@ManyToMany
	private Collection<User> users;
	
	@ManyToOne
	private Sector sector;

	private String completionDate;
	private String period;
	private String snooze;
	private String dueDate;

	public Mission(String missionName, String missionDescription, String period, String snooze,
			String dueDate, String completionDate, User...users) {
		this.missionName = missionName;
		this.missionDescription = missionDescription;
		this.users = new HashSet<>(Arrays.asList(users));
		this.period = period;
		this.snooze = snooze;
		this.dueDate = dueDate;
		this.completionDate = completionDate;

	}

	public Mission() {

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

	public String getPeriod() {
		return period;
	}

	public String getSnooze() {
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
