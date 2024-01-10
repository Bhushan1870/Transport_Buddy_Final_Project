package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import com.bezkoder.spring.hibernate.onetomany.model.Vehicles;

public interface TransportersRepository extends JpaRepository<Transporters,Integer>{
	
	//List<Transporters> findByPublished(boolean published);

	List<Transporters> findByTransporternameContaining(String transportername);
	
	@Query("select t from Transporters t where t.username= :username")
	public Transporters findByUsername(String username);
	
	
	@Query("select t from Transporters t where t.username= :username")
	Optional<Transporters> findTransporter(@Param("username") String username);
	
	@Query("select t from Transporters t, Vehicles v where t.username= :username and v.status=2")
	List<Vehicles> findActiveVehiclesTransporter(@Param("username") String username);
	
	

}
