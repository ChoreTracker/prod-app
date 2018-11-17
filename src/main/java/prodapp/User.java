package prodapp;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

@Entity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class User extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.authorizeRequests()
		.antMatchers("/setup/**", "/h2-console/**").hasRole("ADMIN")
		.antMatchers("/setup/mission", "/h2-console/**").hasRole("USER")
		.antMatchers("/css/**", "/js/**").permitAll()
		.anyRequest().authenticated()
		.and()
	.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
	.logout()
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()
//	.csrf().disable()
	.headers().frameOptions().disable();
}
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin"))
		.roles("USER", "ADMIN")
		.and().withUser("user").password(passwordEncoder().encode("user")).roles("USER");
        
    }
    
	private String getLoggedInUser(Model model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof User)
			return ((User) principal).getUserName();
		return principal.toString();
		
	}
	@Id
	@GeneratedValue
	private long id;
	private String userName;

	@ManyToMany(mappedBy = "users")
	private Collection<Mission> missions;
	
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
