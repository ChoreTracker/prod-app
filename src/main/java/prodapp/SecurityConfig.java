package prodapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true ) // Enables @PreAuthorize, etc.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.authorizeRequests()
		.antMatchers("/admin/**","/setup/**", "/h2-console/**").hasRole("ADMIN")
		.antMatchers("/css/**", "/js/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().ignoringAntMatchers("/h2-console/**")
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
    //may be useless code
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

	}
}
