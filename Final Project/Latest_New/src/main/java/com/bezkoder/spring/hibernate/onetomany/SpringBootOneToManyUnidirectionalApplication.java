package com.bezkoder.spring.hibernate.onetomany;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bezkoder.spring.hibernate.onetomany.repository.UsersRepository;

@SpringBootApplication
public class SpringBootOneToManyUnidirectionalApplication{
	
	    @Autowired
	    private UsersRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOneToManyUnidirectionalApplication.class, args);
	}


}
