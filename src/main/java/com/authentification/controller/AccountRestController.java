package com.authentification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authentification.entity.AppUser;
import com.authentification.service.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	//Enregistrer un User	
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm newUser) {

		AppUser appUser = new AppUser();
		
		// Verifier l'existence du User par son nom
		AppUser existingUser = accountService.findAppUserByUsername(newUser.getUserName());
		if(existingUser !=null) {		
			throw new RuntimeException("Ce pseudo est deja occupe");
		}
		
			if(newUser != null 
					&& newUser.getUserName()!=null
					&& !newUser.getUserName().isEmpty()
					&& newUser.getPassword()!=null 
					&& !newUser.getPassword().isEmpty()
					&& newUser.getConfirmPassword()!=null 
					&& !newUser.getConfirmPassword().isEmpty()
					&& newUser.getPassword().equals(newUser.getConfirmPassword())
					) {
				appUser.setUserName(newUser.getUserName());
				appUser.setPassword(newUser.getPassword());
				accountService.saveUser(appUser);
				accountService.addRoleToUser(newUser.getUserName(), "USER");
			}
			else {
			  throw	new RuntimeException("les mots de passes saisis ne sont pas identiques");
			}
				
		return appUser;
		
	}
}
