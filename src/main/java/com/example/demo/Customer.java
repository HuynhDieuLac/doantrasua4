package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Customer {
	
//@Autowired
//	private Person person;
	
	private final Person person;
	
	Customer(Person person){
		this.person = person;
		
	}

	public String getPersonName() {
		return person.getName();
	}
}
