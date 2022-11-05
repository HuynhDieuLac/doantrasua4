package model;

import java.util.List;

import com.example.demo.entities.Food;
import com.example.demo.entities.FoodImage;

public class FoodInfo {
	private Long foodId;
	private String foodName;
	private long foodPrice;
	private String foodImage;
	
	public FoodInfo()
	{
		
	}
	
	public FoodInfo(Food foods)
	{
		this.foodId = foods.getId();
		this.foodName = foods.getFoodName();
		this.foodPrice = foods.getPrice();
		this.foodImage = foods.getThumbnailImageName();
		
	}
	
	
	public FoodInfo(Long foodId, String foodName, int foodPrice, String foodImage) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodImage = foodImage;
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public long getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(long foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(String foodImage) {
		this.foodImage = foodImage;
	}

	
	
	
}
