package prodapp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Sector {
	
	@Id
	@GeneratedValue
	private long id;

	private String sectorName;
	
	@OneToMany (mappedBy="sector")
	private Collection<Mission> missions;

	private String imageUrl;
	
	public Collection<Mission> getMissions() {
		return missions;
	}
	
	public String getSectorName() {
		return sectorName;
	}
	
	public long getId() {
		return id;
	}
	
//	public void addMission(Mission mission) {
//		this.missions.add(mission);
//	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public Sector() {
		
	}
	public Sector(String sectorName, String imageUrl) {
		this.sectorName = sectorName;
		this.imageUrl = imageUrl;
		
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
		Sector other = (Sector) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
