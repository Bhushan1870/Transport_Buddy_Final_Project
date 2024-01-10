package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.hibernate.onetomany.model.FinalOrder;

public interface FinalOrderRepository extends JpaRepository<FinalOrder,Integer>{
	
	//List<Tutorial> findByPublished(boolean published);

	List<FinalOrder> findByOrderid(String theid);

}
