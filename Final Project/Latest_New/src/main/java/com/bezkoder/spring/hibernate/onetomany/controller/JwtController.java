package com.bezkoder.spring.hibernate.onetomany.controller;

import com.bezkoder.spring.hibernate.onetomany.helper.JwtUtil;
import com.bezkoder.spring.hibernate.onetomany.model.JwtRequest;
import com.bezkoder.spring.hibernate.onetomany.model.JwtResponse;
import com.bezkoder.spring.hibernate.onetomany.repository.UsersRepository;
import com.bezkoder.spring.hibernate.onetomany.services.CustomUserDetailsImpl;
import com.bezkoder.spring.hibernate.onetomany.services.CustomUserDetailsService;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class JwtController {

	@Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UsersRepository userRepository;


	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtil jwtUtils;

	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser( @RequestBody JwtRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();    
	    

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getUserId(), 
	                         userDetails.getUsername()
	                         
	                         ));
	  }

	 
}
