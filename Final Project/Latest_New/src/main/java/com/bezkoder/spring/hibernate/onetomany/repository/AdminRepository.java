package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.hibernate.onetomany.model.Admin;
import com.bezkoder.spring.hibernate.onetomany.model.Transporters;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
	
	@Query("select a from Admin a where a.username= :username and a.password= :password")
	Optional<Admin> findAdmin(@Param("username") String username,@Param("password") String password);
	
	@Query(value="select count(requestid) from Requests")
	public int getRequestsCount();
	
	@Query("select a from Admin a where a.username= :username")
	Optional<Admin> findAdmin(@Param("username") String username);

	Admin findByUsername(String username);

}
