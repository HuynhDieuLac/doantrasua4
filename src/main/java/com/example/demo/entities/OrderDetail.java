package com.example.demo.entities;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {

	@Id
	@Column(name = "order_detail_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "order_detail_price")
	private long price;
	
	@Column(name = "order_detail_quantity")
	private long quantity;
	
	@Column(name ="order_detail_amount")
	private double amount;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Order order;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Food food;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Order getOrderId() {
		return order;
	}

	public void setOrderId(Order orderId) {
		this.order = orderId;
	}
	

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
