package com.bezkoder.spring.hibernate.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.hibernate.onetomany.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer>{

}
