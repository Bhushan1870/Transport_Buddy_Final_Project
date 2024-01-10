package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.hibernate.onetomany.model.Requests;
import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Vehicles;

public interface RequestsRepository extends JpaRepository<Requests,Integer>{
	
	@Query("select r from Requests r where r.status=1")
//	Optional<List<Vehicles>> findActiveVehiclesTransporter(@Param("id") int id);
	Optional<List<Requests>> findPendingRequests();

	@Query("select r from Requests r where r.userid= :userid and status=1")
	List<Requests> findByUserId(int userid);
	
	@Query("select r from Requests r where r.userid= :userid and status=2")
	List<Requests> findActiveByUserId(int userid);
	
	@Query("select r from Requests r where r.userid= :userid and status=3")
	List<Requests> findCompletedTripsByUserId(int userid);

	@Query("select r from Requests r where r.status=2")
	Optional<List<Requests>> findActiveRequests();

}
