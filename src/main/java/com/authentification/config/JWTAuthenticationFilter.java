package com.authentification.config;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authentification.entity.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/*
 * Filtres  des authentifications avec JWT By Cisse
 * */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
		
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    
     // Se connecter avec un objet Json
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
                                               
        try {
            AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
  
            return authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( appUser.getUserName(),  appUser.getPassword()));                  
          
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    //Formation du token en cas d'authentification avec succes
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

    	
             User userSpring = (User) authResult.getPrincipal();
             String jwtoken = Jwts.builder()
            		          .setSubject(userSpring.getUsername())
            		          .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            		          .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
            		          .claim("roles",userSpring.getAuthorities())
            		          .compact();
             response.setHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwtoken );
               
    }
}
