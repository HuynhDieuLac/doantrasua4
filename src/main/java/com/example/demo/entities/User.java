package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Username is mandatory")
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@NotBlank(message = "Password is mandatory")
	@Column(name = "password", nullable = false)
	private String password;
	
	@NotBlank(message = "First name is mandatory")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank(message = "Last name is mandatory")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email")
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "PHONE_NUMBER", length = 20)
	private String phoneNumber;
	
	@Column(name = "IS_ACTIVE", length = 1)
	private Boolean isActive=false;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Department department;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Permission> permissions;
	
	@Column(name = "PROFILE_IMAGE_NAME", nullable = true)
	private String profileImageName;
	
	@Transient
	private MultipartFile profileImageFile = null;

	public User() {
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public MultipartFile getProfileImageFile() {
		return profileImageFile;
	}

	public void setProfileImageFile(MultipartFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
	
	
}
