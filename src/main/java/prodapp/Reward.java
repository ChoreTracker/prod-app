package prodapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Reward {

	@Id
	@GeneratedValue
	private long rewardId;
	private String rewardName;
	private String rewardDescription;
	private int rewardValue;
	private boolean earned;
		
	public Reward() {
		
	}
	
	public Reward(String rewardName, String rewardDescription, int rewardValue, boolean earned) {
		this.rewardName = rewardName;
		this.rewardDescription = rewardDescription;
		this.rewardValue = rewardValue;
		this.earned = earned;
	}


	public boolean isEarned() {
		return earned;
	}


	public void setEarned(boolean earned) {
		this.earned = earned;
	}


	public long getId() {
		return rewardId;
	}


	public String getRewardName() {
		return rewardName;
	}


	public String getRewardDescription() {
		return rewardDescription;
	}


	public int getRewardValue() {
		return rewardValue;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rewardId ^ (rewardId >>> 32));
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
		Reward other = (Reward) obj;
		if (rewardId != other.rewardId)
			return false;
		return true;
	}

}
