package com.bezkoder.spring.hibernate.onetomany.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bezkoder.spring.hibernate.onetomany.services.CustomUserDetailsImpl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//methods - for generating token
//validate
//isExp
//util class for jwt
@Component
public class JwtUtil {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	  @Value("${bezkoder.app.jwtSecret}")
	  private String jwtSecret;

	  @Value("${bezkoder.app.jwtExpirationMs}")
	  private int jwtExpirationMs;

	  public String generateJwtToken(Authentication authentication) {

	    CustomUserDetailsImpl userPrincipal = (CustomUserDetailsImpl) authentication.getPrincipal();

	    return Jwts.builder()
	        .setSubject((userPrincipal.getUsername()))
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	        .signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	  }

	  public String getUserNameFromJwtToken(String token) {
	    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	  }

	  public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (SignatureException e) {
	      logger.error("Invalid JWT signature: {}", e.getMessage());
	    } catch (MalformedJwtException e) {
	      logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	      logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	      logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	      logger.error("JWT claims string is empty: {}", e.getMessage());
	    }

	    return false;
	  }
}
