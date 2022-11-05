package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findAll(Pageable pageable);
	
	@Query("select c from Category as c where code = :ccode")
	Category findByCategoryName(@Param("ccode") String categoryName);
}
