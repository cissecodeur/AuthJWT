package com.authentification.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/*
 * Filtres des authorisations avec JWT By Cisse
 * */

public class JWTAuthorizationFilter extends OncePerRequestFilter {

   

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterchain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
        	filterchain.doFilter(request, response);
            return;
        }
           // Si le header est pas null on set un token en enlevant le prefix
        Claims claim = Jwts.parser()
        		       .setSigningKey(SecurityConstants.SECRET)
                       .parseClaimsJws(header.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody();
         
        
        //Recuperer la liste des elements du token username et son role
        String Username = claim.getSubject();
            
        @SuppressWarnings("unchecked")
		ArrayList<Map<String, String>> roles =    (ArrayList<Map<String, String>>) claim.get("roles");   
        List<GrantedAuthority> authorities = new ArrayList<>();
        		                       roles.stream().forEach(r->{
        		                    	   authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        		                       });
        
        		                       // on ajoute ainsi les elements objet a un attribut userAuthentitié
        UsernamePasswordAuthenticationToken userAuthentified =  new UsernamePasswordAuthenticationToken (Username,null,authorities);

        SecurityContextHolder.getContext().setAuthentication(userAuthentified);
        filterchain.doFilter(request, response);
    }

    	
    
}