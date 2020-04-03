package com.authentification.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

	 private int id;
	 private String userName;
	 private String password;
	 private String confirmPassword;
}
