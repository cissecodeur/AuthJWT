package com.authentification.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentification.entity.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	

	@Autowired
	private AccountService accountService;
	
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
        AppUser appUser = accountService.findAppUserByUsername(userName);
         
        if(appUser == null) {
        	  throw new  UsernameNotFoundException(userName);
          }
        else {
        	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        	if(appUser != null
        			&& appUser.getDatasRoles() !=null
        			&& !appUser.getDatasRoles().isEmpty()) {
        		appUser.getDatasRoles().stream().forEach(r->{
            		authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            	});
            	
        	}
        	
         return new User(appUser.getUserName(),appUser.getPassword(),authorities);
        }

       
    }
   
}