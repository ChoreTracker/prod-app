package prodapp;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

@Entity
@Configuration
@EnableWebSecurity
public class User extends WebSecurityConfigurerAdapter{
	@Id
	@GeneratedValue
	private long id;
	private String userName;

	@ManyToMany(mappedBy = "users")
	private Collection<Mission> missions;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) 
			throws Exception {
				auth.inMemoryAuthentication().withUser("Admin").password("admin")
				.roles("USER", "ADMIN");
			}
	
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

	private String contact;

	public User(String userName, String contact) {
		this.userName = userName;
		this.contact = contact;
	}
	private String getLoggedInUser(Model model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof User)
			return ((User) principal).getUserName();
		return principal.toString();
		
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
