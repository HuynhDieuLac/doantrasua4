package com.example.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "First name is mandatory")
	@Column(name = "customer_first_name", nullable = false)
	private String firstName;
	
	@NotBlank(message = "Last name is mandatory")
	@Column(name = "customer_last_name", nullable = false)
	private String lastName;
	
	@NotBlank(message = "Street is mandatory")
	@Column(name = "customer_street", nullable = false)
	private String street;
	
	@NotBlank(message = "District is mandatory")
	@Column(name = "customer_district", nullable = false)
	private String district;
	
	@NotBlank(message = "City is mandatory")
	@Column(name = "customer_city", nullable = false)
	private String city;
	
	@Email(message = "Invalid email")
	@Column(name = "customer_email")
	private String email;
	
	@Column(name = "PHONE_NUMBER", length = 20)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
