package prodapp;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User{
	
	@Id
	@GeneratedValue
	private long id;
	
	private String userName;
	private String contact;
	
	@JsonIgnore
	private String password;
	private String[] roles;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getRoles() {
		return roles;
	}

	@ManyToMany(mappedBy = "users")
	private Collection<Mission> missions;

	private String theme;


	private int rewardBalance;

	private String avatar;


	public User() {
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getContact() {
		return contact;
	}

	public Collection<Mission> getMissions() {
		return missions;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getTheme() {
		return theme;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	

	public int getRewardBalance() {
		return rewardBalance;
	}

	public void setRewardBalance(int rewardBalance) {
		this.rewardBalance = rewardBalance;
	}


	public User(String userName, String password, String avatar, String contact, String theme, int rewardBalance, String...roles) {

		this.userName = userName;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.avatar = avatar;
		this.contact = contact;
		this.theme = theme;
		this.rewardBalance = rewardBalance;
		this.roles = roles;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
