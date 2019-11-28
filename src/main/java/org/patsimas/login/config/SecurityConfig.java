package org.patsimas.login.config;

import org.springframework.beans.factory.annotation.Autowired;
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

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// first set type of authentication you want
		
//		auth.inMemoryAuthentication()
//			.withUser("andreas")
//			.password(passwordEncoder().encode("areianara"))
//			.roles("USER")
//			.and()
//			.withUser("aris")
//			.password(passwordEncoder().encode("1914"))
//			.roles("ADMIN");
		
		auth.userDetailsService(userDetailsService);
	}
	
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/", "static/css", "static/js").permitAll()
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
	
	
   /* https://www.browserling.com/tools/bcrypt*/
}
