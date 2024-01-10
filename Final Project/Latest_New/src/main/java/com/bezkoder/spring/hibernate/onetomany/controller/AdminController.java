package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Admin;
import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.repository.AdminRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	private AdminRepository adminrepository ;
	
	 @GetMapping("/admin")
	  public ResponseEntity<Admin> getAdminById(@RequestParam("username") String username) {
		 Admin admin = adminrepository.findAdmin(username)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found User with username = " + username));

	    return new ResponseEntity<>(admin, HttpStatus.OK);
	  }
	
	@GetMapping("/adminlogin")
	  public String adminLogin(@RequestParam("username") String username,@RequestParam("password") String password)
	  {
		  Optional<Admin> admin = adminrepository.findAdmin(username,password) ;
		  if(admin.isEmpty())
			  return "Admin not found";
		  else
			  System.out.println(admin.get());
			  return "Success";
	  }
	
	 @GetMapping("/requests/count")
	 public int getCount()
	 {
	     return adminrepository.getRequestsCount();
	 }

}
