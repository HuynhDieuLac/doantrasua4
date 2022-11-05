package com.example.demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping({"/{userId}"})
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
//	public ResponseEntity<User> getUser(@RequestParam(name="id", required=false, defaultValue="1") Long userId) {
		User user = userService.findUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
	User User1 = userService.saveUser(user);
	HttpHeaders httpHeaders = new HttpHeaders();
	return new ResponseEntity<>(User1, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping({"/{userId}"})
	public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user, String uploadRootPath) {
		userService.updateUser(user, userId, uploadRootPath);
		User savedUser = userService.findUserById(userId);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
	@DeleteMapping({"/{userId}"})
	public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
	userService.deleteUser(userId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
}
