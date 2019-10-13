package org.patsimas.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// first set type of authentication you want
		
		auth.inMemoryAuthentication()
			.withUser("andreas")
			.password(passwordEncoder().encode("areianara"))
			.roles("USER")
			.and()
			.withUser("aris")
			.password(passwordEncoder().encode("1914"))
			.roles("ADMIN");
	}
	
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/", "static/css", "static/js").permitAll()
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin").hasRole("ADMIN")
			.and().formLogin();
			
			
		
	}





	//	@Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//          .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//          .and()
//          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//          .and()
//          .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//    }
// 
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//          .csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/admin/**").hasRole("ADMIN")
//          .antMatchers("/anonymous*").anonymous()
//          .antMatchers("/login*").permitAll()
//          .anyRequest().authenticated()
//          .and()
//          .formLogin()
//          .loginPage("/login.html")
//          .loginProcessingUrl("/perform_login")
//          .defaultSuccessUrl("/teers.html", true)
//          //.failureUrl("/login.html?error=true")
//          //.failureHandler()
//          .and()
//          .logout()
//          .logoutUrl("/perform_logout")
//          .deleteCookies("JSESSIONID");
//          //.logoutSuccessHandler();
//    }
//    
//
//     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
   /* https://github.com/hantsy/springboot-jwt-sample
    * https://www.baeldung.com/spring-security-authentication-with-a-database
    * https://github.com/eugenp/tutorials/tree/master/spring-security-mvc-boot
    https://www.youtube.com/watch?v=sm-8qfMWEV8&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE
    * */
}
