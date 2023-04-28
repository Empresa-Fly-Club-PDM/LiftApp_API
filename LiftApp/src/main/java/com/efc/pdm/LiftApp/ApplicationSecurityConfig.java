package com.efc.pdm.LiftApp;

import com.efc.pdm.LiftApp.jwt.JwtTokenFilter;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled=false,
		securedEnabled=false,
		jsr250Enabled=true
		)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired private UserRepository userRepo;
	@Autowired private JwtTokenFilter jwtTokenfilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userRepo.findByEmail(username)
				.orElseThrow(()->new UsernameNotFoundException("User "+username+" not found"))
				);
	}
	
	


	@Override @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(
				(request,response,ex)->{
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					ex.getMessage());
				}
				);
		
		http.authorizeRequests()
			.antMatchers("/auth/**").permitAll()
			.anyRequest().authenticated();
		
		http.addFilterBefore(jwtTokenfilter, UsernamePasswordAuthenticationFilter.class);
	}
	

}
