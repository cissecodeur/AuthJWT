package com.authentification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authentification.dao.AppRoleRepository;
import com.authentification.dao.AppUserRepository;
import com.authentification.entity.AppRole;
import com.authentification.entity.AppUser;


@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Override
	public AppUser saveUser(AppUser user) {
		
		if(user !=null  && user.getPassword() != null 
				        && !user.getPassword().isEmpty())
			         			  
		{
		String password = user.getPassword();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		 user.setPassword(encodedPassword);
		}
	 
		return appUserRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
	
		return appRoleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		
		AppRole role = appRoleRepository.findByRoleName(roleName);
		AppUser user = appUserRepository.findByUsername(userName);
		user.getDatasRoles().add(role);
		
	}

	@Override
	public AppUser findAppUserByUsername(String userName) {
		return appUserRepository.findByUsername(userName);
		
	}

}