package com.example.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Permission;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StorageService storageService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final String DEFAULT_INITIAL_PASSWORD = "123456";

	public void createDefaultAdmin() throws Exception {
		// TODO create all groups and permissions
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		// create permission
		Permission adminPermission = new Permission();
		adminPermission.setRole("ADMIN");
		List<Permission> permissions = new ArrayList<>();
		permissions.add(adminPermission);
		
		//create new user
		User user = new User();
		user.setUserName("admin");
		user.setPassword(password);
		user.setEmail("admin@greenacedamy.com");
		user.setFirstName("Administrator");
		user.setLastName("User");		
		user.setIsActive(true);
		user.setPermissions(permissions);
		userRepository.save(user);
	}

	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setIsActive(true);
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	public User saveUser(User user, String uploadRootPath) {
		File file = storageService.store(user.getProfileImageFile(), uploadRootPath);//		
		user.setProfileImageName(file.getName());
		this.saveUser(user);
		return user;
	}

	public User updateUser(User user, Long id, String uploadRootPath) {
		User currentUser = findUserById(id);
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPhoneNumber(user.getPhoneNumber());
		currentUser.setUserName(user.getUserName());
		currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
		currentUser.setPermissions(user.getPermissions());
		File file = storageService.store(user.getProfileImageFile(), uploadRootPath);
		if(file!=null) {
			user.setProfileImageName(file.getName());
			currentUser.setProfileImageName(user.getProfileImageName());
		}
		
		return userRepository.save(currentUser);
	}
	
	public User updateProfile(User user, Long id, String uploadRootPath) {
		User currentUser = findUserById(id);
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPhoneNumber(user.getPhoneNumber());
		currentUser.setUserName(user.getUserName());
		currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
		File file = storageService.store(user.getProfileImageFile(), uploadRootPath);
		if (file!=null) {
			user.setProfileImageName(file.getName());
			currentUser.setProfileImageName(user.getProfileImageName());
		}
		
		return userRepository.save(currentUser);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public User findUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.get();
	}

	public User getByUserName(String userName) {
		return userRepository.findByUserName(userName);

	}

	public Page<User> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<User> pageUser = userRepository.findAll(pageable);
		return pageUser;
	}
}
