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
	

	
//	?Is this a method that runs on any user? like user.findLoggedInUser();
//	private User findLoggedInUser() {
//		Object activeUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User loggedInUser = User.class.cast(activeUser);
//		return loggedInUser;
//	}

	public User(String userName, String password, String contact, String theme, String...roles) {
		this.userName = userName;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.contact = contact;
		this.theme = theme;
		this.roles = roles;
		
	}


	public String getTheme() {
		return theme;
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
