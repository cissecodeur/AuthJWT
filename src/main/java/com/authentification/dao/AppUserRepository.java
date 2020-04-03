package com.authentification.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.authentification.entity.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	@Query("select e from AppUser e where e.userName = :userName")
	public  AppUser findByUsername(@Param ("userName") String userName);

}
