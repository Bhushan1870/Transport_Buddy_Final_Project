package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Users;


public interface UsersRepository extends JpaRepository<Users,Integer>{
	
	  //List<Users> findByPublished(boolean published);

	  List<Users> findByUsernameContaining(String title);
	  
	  public Users findByUsername(String username);
	  
	  @Query("select u from Users u where u.username= :username")
	  Optional<Users> findUser(@Param("username") String username);

}
