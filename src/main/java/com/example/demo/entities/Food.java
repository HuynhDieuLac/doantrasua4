package com.example.demo.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "food")
public class Food {

	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="food_name", nullable = false)
	@NotBlank(message="Name is mandatory")
	private String foodName;
	
	@NotBlank(message = "Code is mandatory")
	@Column(name = "food_code", nullable = false)
	private String foodCode;

	@Column(name = "food_description")
	private String description;

	@NotNull(message = "Quantity is mandatory")
	@Column(name = "food_quantity", nullable = false)
	private long quantity;

	@NotNull(message = "Price is mandatory")
	@Column(name = "food_price", nullable = false)
	private long price;

	@Column(name = "food_price_special")
	private long priceSpecial;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "food_price_special_start")
	private Date priceSpecialStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "food_price_special_end")
	private Date priceSpecialEnd;

	@Column(name = "IS_AVAILABLE", length = 1)
	private Boolean isAvailable=false;

	@Column(name = "IS_FREE_FREESHIPPING", length = 1)
	private Boolean isFreeShipping=false;	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
		
	@OneToMany(mappedBy = "food")
	private List<FoodAttribute> attributes;
	
	@OneToMany(mappedBy = "food")
	private List<Food> food;
	
	@OneToMany(mappedBy="food")
	private List<OrderDetail> orderDetail;
	
	@OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
	private List<FoodImage> images = new ArrayList<FoodImage>();
	
	@Transient
	private List<MultipartFile> foodImageFiles = null;
	
	@Column(name = "THUMBNAIL_IMAGE_NAME", nullable = true)
	private String thumbnailImageName;
	
	@Transient
	private MultipartFile thumbnailImageFile = null;

	public List<Food> getFood() {
		return food;
	}

	public void setFood(List<Food> food) {
		this.food = food;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return foodCode;
	}

	public void setCode(String code) {
		this.foodCode = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getPriceSpecial() {
		return priceSpecial;
	}

	public void setPriceSpecial(long priceSpecial) {
		this.priceSpecial = priceSpecial;
	}

	public Date getPriceSpecialStart() {
		return priceSpecialStart;
	}

	public void setPriceSpecialStart(Date priceSpecialStart) {
		this.priceSpecialStart = priceSpecialStart;
	}

	public Date getPriceSpecialEnd() {
		return priceSpecialEnd;
	}

	public void setPriceSpecialEnd(Date priceSpecialEnd) {
		this.priceSpecialEnd = priceSpecialEnd;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Boolean getIsFreeShipping() {
		return isFreeShipping;
	}

	public void setIsFreeShipping(Boolean isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	public List<FoodImage> getImages() {
		return images;
	}

	public void setImages(List<FoodImage> images) {
		this.images = images;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<FoodAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<FoodAttribute> attributes) {
		this.attributes = attributes;
	}

	public String getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}

	public List<MultipartFile> getFoodImageFiles() {
		return foodImageFiles;
	}

	public void setFoodImageFiles(List<MultipartFile> foodImageFiles) {
		this.foodImageFiles = foodImageFiles;
	}

	public String getThumbnailImageName() {
		return thumbnailImageName;
	}

	public void setThumbnailImageName(String thumbnailImageName) {
		this.thumbnailImageName = thumbnailImageName;
	}

	public MultipartFile getThumbnailImageFile() {
		return thumbnailImageFile;
	}

	public void setThumbnailImageFile(MultipartFile thumbnailImageFile) {
		this.thumbnailImageFile = thumbnailImageFile;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	
}
