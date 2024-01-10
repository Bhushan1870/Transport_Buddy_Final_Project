package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Vehicles;

public interface VehiclesRepository extends JpaRepository<Vehicles,Integer>{
	
	@Query(value="select count(vehicleid) from Vehicles")
	public int getVehiclesCount();
	
	@Query("select v from Vehicles v where v.status= 3")
	List<Vehicles> findActiveVehicles();
	
//	@Query("select v from Vehicles v where v.userid=:id and v.status= 1")
//	List<Vehicles> findRequestedVehiclesUsers(@Param("id") int id);
	
//	@Query("select v from Vehicles v where  v.transporterid= :id")
//	Optional<List<Vehicles>> findActiveVehiclesTransporter(@Param("id") int id);
	
	@Query("select v from Vehicles v where  v.transporterid= :id and status=3")
	Optional<List<Vehicles>> findActiveVehiclesTransporter(@Param("id") int id);
	
	@Query("select v from Vehicles v where  v.transporterid= :id and status=2")
	Optional<List<Vehicles>> findPendingVehiclesTransporter(@Param("id") int id);
	
	@Query("select v from Vehicles v where  v.transporterid= :id and status=1")
	Optional<List<Vehicles>> findRequestedVehiclesTransporter(@Param("id") int id);
	
	@Query("select v from Vehicles v where  v.transporterid= :id and status=4")
	Optional<List<Vehicles>> findCompletedVehiclesTransporter(@Param("id") int id);
	
	@Query("select v from Vehicles v where  v.transporterid= :id and status=5")
	Optional<List<Vehicles>> findCancelledVehiclesTransporter(@Param("id") int id);

	
	@Query("select v from Vehicles v where v.status= 2")
	List<Vehicles> findAllPendingVehicles();
	
	@Query("select v from Vehicles v where v.status= 1")
	List<Vehicles> findAllRequestedVehicles();
	
	@Query("select v from Vehicles v where v.status= 4")
	List<Vehicles> findAllCompletedVehicles();
	
//	@Query("select v from Vehicles v where v.status= : status")
//	Optional<Vehicles> findActiveVehiclesTransporter(@Param("status") int status);
	

}
