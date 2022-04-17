package com.olx.login.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;


//This is a centralized Class in entire Application. The Entire Security Of My Application written inside
// or Configured inside this Class
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;
//	
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // this method used for Authentication
//		auth.inMemoryAuthentication()
//		.withUser("tom").password(this.passwordEncoder.encode("suraj123")).roles("USER")
//		.and()
//		.withUser("Jerry").password(this.passwordEncoder.encode("jerry123")).roles("ADMIN");
		auth.userDetailsService(userDetailsService);
		
	}
	
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
        //Used for Authorization
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user").hasAnyRole("USER", "ADMIN") 
		.antMatchers("/admin").hasAnyRole("ADMIN")
		.antMatchers("/olx/login/user/authenticate").permitAll()
		.and()
		.formLogin();
	}
	
	@Bean 
	public AuthenticationManager getAuthenticationManager() throws Exception {
	    return super.authenticationManager(); 
	}

}

