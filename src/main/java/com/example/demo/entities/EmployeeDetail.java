package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeeDetail")
public class EmployeeDetail {
@Id
@Column(name = "id")
private int id;

@Column(name = "age")
private int age;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

}
