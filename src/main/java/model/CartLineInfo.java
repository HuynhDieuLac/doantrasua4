package model;



public class CartLineInfo {
	private FoodInfo foodInfo;
	private int quantity;
	
	public CartLineInfo() {
	this.quantity = 0;
	}
	
	public FoodInfo getFoodInfo() {
	return foodInfo;
	}
	
	public void setFoodInfo(FoodInfo foodInfo) {
	this.foodInfo = foodInfo;
	}
	
	public int getQuantity() {
	return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getAmount() {
		return this.foodInfo.getFoodPrice() * this.quantity;
	}
		
		
}