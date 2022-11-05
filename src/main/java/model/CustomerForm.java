package model;

import com.example.demo.CustomerInfo;

public class CustomerForm {
	private String firstName;
	private String lastName;
	private String street;
	private String district;
	private String city;
	private String email;
	private String numberPhone;
	private boolean valid;

	public CustomerForm() {
	}

	public CustomerForm(CustomerForm customerForm) {
		if (customerForm != null) {
			this.firstName = customerForm.getFirstName();
			this.lastName = customerForm.getLastName();
			this.street = customerForm.getStreet();
			this.district = customerForm.getDistrict();
			this.city = customerForm.getCity();
			this.email = customerForm.getEmail();
			this.numberPhone = customerForm.getNumberPhone();
		}
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

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
