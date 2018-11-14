package prodapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;


@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true )
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfig {
	@Autowired
	protected void configureAuth(AuthenticationManagerBuilder auth) 
			throws Exception {
				auth.inMemoryAuthentication().withUser("admin").password("{noop}admin")
				.roles("USER", "ADMIN")
				.and().withUser("user").password("{noop}user").roles("USER");
			}
	
	private String getLoggedInUser(Model model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof User)
			return ((User) principal).getUserName();
		return principal.toString();
		
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
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
			.csrf().disable()
			.headers().frameOptions().disable();
	}
}
