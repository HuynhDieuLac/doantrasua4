package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Page<Order> findAll(Pageable pageable);
}
