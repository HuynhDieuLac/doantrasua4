package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Vehicle {
	
	@Bean
	Vehicle engine() {
		return new Vehicle();
	}
	
	@Bean
	Person person() {
		return new Person();
	}
	
	@Bean
	Customer customer(Person person) {
		return new Customer(person);
	}
}
