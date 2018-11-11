package prodapp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Sector {
	
	@Id
	@GeneratedValue
	private long id;

	private String sectorName;
	
	@OneToMany
	private Collection<Mission> missions;
	
	public Collection<Mission> getMissions() {
		return missions;
	}
	
	public String getSectorName() {
		return sectorName;
	}
	
	public long getId() {
		return id;
	}
	
	public void addMission(Mission mission) {
		this.missions.add(mission);
	}
	
	public Sector() {
		
	}
	public Sector(String sectorName, Mission...missions) {
		this.sectorName = sectorName;
		this.missions = new HashSet<>(Arrays.asList(missions));
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
