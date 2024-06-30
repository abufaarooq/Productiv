package com.productiv.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityBasicAuthConfig {
	
	@Autowired
	UserDetailsService user;
	
	@Autowired
   JWTEntryPoint authEntryPoint;


	
	/* The below code block allows access to the H2 console */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().anyRequest();
    }


	   @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		   
	       http
           .csrf().disable()
		   .exceptionHandling()
		   .authenticationEntryPoint(authEntryPoint)
		   .and()
		   .sessionManagement()
		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		   .and()
           .authorizeHttpRequests()
           .anyRequest()
           .fullyAuthenticated();
		   http.addFilterBefore(jwtAuthencticationFilter(), UsernamePasswordAuthenticationFilter.class);
//		   http
//	    		.authorizeHttpRequests()
////	    		.requestMatchers("/h2/**").permitAll()
//	    		.requestMatchers("/api/v1/registration/**").permitAll()
//	    		.requestMatchers("/api/v1/login/**").permitAll()
//	    		.anyRequest().authenticated()
//	    		.and()
//	    		.headers().frameOptions().disable()
//	    		.and()
////	    		.httpBasic(Customizer.withDefaults())
//	    		.userDetailsService(user)
//	    	    .csrf().disable();
	    		 return http.build();

		    }

	   @Bean
	   public BCryptPasswordEncoder passwordEncoder()
	   {
		   return new BCryptPasswordEncoder();
	   }
	   
	   @Bean
	   public AuthenticationManager authenticationManager (AuthenticationConfiguration auth) throws Exception {
		   return auth.getAuthenticationManager();
	   }

	   @Bean
	   AuthenticationProvider authenticationProvider() 
	   {
		   DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		   provider.setUserDetailsService(user);
		   provider.setPasswordEncoder(new BCryptPasswordEncoder());
		   return provider;
   }

   @Bean
   public JWTAuthencticationFilter jwtAuthencticationFilter() {
	return new JWTAuthencticationFilter();
   }

}