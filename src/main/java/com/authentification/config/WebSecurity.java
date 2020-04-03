package com.authentification.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authentification.service.UserDetailsServiceImpl;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	 @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http.csrf().disable();
                 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);     // Desactiver l'authentification par session
                 http.authorizeRequests()
                .antMatchers("/login/**","/register/**").permitAll()
         	    .antMatchers(HttpMethod.POST,"/task/**").hasAnyAuthority("ADMIN") // Seul un Admin peut faire un POST
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
            
    }

   


}