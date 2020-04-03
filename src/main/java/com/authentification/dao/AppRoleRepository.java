package com.authentification.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.authentification.entity.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	
	
	@Query("select e from AppRole e where e.roleName = :roleName")
	public AppRole  findByRoleName(@Param("roleName")String roleName);
	

}
