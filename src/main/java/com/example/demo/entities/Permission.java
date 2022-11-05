package com.example.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;
	
	@Column(name = "role")
	private String role;
	
	@ManyToMany(mappedBy = "permissions")
	private List<Employee> employee;
	
	@ManyToMany(mappedBy = "permissions")
	private List<User> users;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
