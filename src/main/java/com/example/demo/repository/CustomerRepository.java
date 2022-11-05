package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Page<Customer> findAll(Pageable pageable);
	
	@Query("select c from Customer as c where name = :cname")
	Customer findByCustomerName(@Param("cname") String customerName);
}
