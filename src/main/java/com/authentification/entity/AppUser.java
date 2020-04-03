package com.authentification.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity

@Table(name = "App_User")
public class AppUser {
	
	@Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    private String userName;
    
    
    @Column(length = 128)
    private String password;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> DatasRoles = new ArrayList<>();


	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AppUser(Long id, String userName, String password, List<AppRole> datasRoles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		DatasRoles = datasRoles;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}


	public List<AppRole> getDatasRoles() {
		return DatasRoles;
	}


	public void setDatasRoles(List<AppRole> datasRoles) {
		DatasRoles = datasRoles;
	} 
 
//    @Column(name = "Enabled", length = 1, nullable = false)
//    private boolean enabled;
    
    
    

}
