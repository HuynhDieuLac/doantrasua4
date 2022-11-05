package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "foodImage")
public class FoodImage {

	@Id
	@Column(name = "food_image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "food_image")
	private String imageName;
	
	@Column(name = "food_default_image")
	private String defaultImage;
	
	@Column(name = "food_image_url")
	private String imageURL;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Food food;
	
	@Transient
	private MultipartFile foodImageFile = null;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public MultipartFile getFoodImageFile() {
		return foodImageFile;
	}

	public void setFoodImageFile(MultipartFile foodImageFile) {
		this.foodImageFile = foodImageFile;
	}

	
	
	
}
