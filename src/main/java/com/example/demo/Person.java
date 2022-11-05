package com.example.demo;

import org.springframework.stereotype.Component;

public class Person {
	private String name="David";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
