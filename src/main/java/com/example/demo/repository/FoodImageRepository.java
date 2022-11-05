package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.FoodImage;

@Repository
public interface FoodImageRepository extends JpaRepository<FoodImage, Long>{
	
	@Query("select i from FoodImage as i where i.food.id = :foodId")
	public List<FoodImage> getImageByFoodId(@Param("foodId") long foodId);

	@Transactional
	@Modifying
	@Query("delete from FoodImage as f where f.food.id = :foodId")
	public void deleteByFoodId(@Param("foodId") long foodId);
}
