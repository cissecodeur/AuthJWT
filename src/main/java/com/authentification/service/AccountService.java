package com.authentification.service;

import com.authentification.entity.AppRole;
import com.authentification.entity.AppUser;

public interface AccountService {

	 public AppUser saveUser(AppUser user);
	 public AppRole saveRole(AppRole role);
	 public void    addRoleToUser(String userName, String roleName);
	 public AppUser    findAppUserByUsername(String userName);
}
