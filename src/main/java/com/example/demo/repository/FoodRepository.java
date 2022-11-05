package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{

	Page<Food> findAll(Pageable pageable);
	
	@Query("select f from Food as f where foodCode = :fcode")
	Food findByFoodCode(@Param("fcode") String foodCode);
	
	@Query("select f from Food as f where f.category.id = :categoryId")
	Page<Food> findByCategoryId(Pageable pageable, @Param("categoryId") long categoryId);
	
}
