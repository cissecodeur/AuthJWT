package com.authentification.config;

public class SecurityConstants {
	
	public static final String SECRET = "yacoub@gmail.com";
	public static final long EXPIRATION_TIME = 864_000_000; //10jours
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";;
    public static final String SIGN_UP_URL = "/users/register";
}
