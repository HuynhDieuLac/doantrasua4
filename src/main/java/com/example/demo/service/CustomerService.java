package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;

import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getCustomers() {
		return this.customerRepository.findAll();
	}

	public Customer saveCustomer(Customer customer) {		
		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
	}
		
	public Customer updateCustomer(Customer customer, Long id) {
		Customer currentCustomer = findCustomerById(id);
		currentCustomer.setFirstName(customer.getFirstName());
		currentCustomer.setLastName(customer.getLastName());
		currentCustomer.setStreet(customer.getStreet());
		currentCustomer.setDistrict(customer.getDistrict());
		currentCustomer.setCity(customer.getCity());
		currentCustomer.setEmail(customer.getEmail());
		currentCustomer.setPhoneNumber(customer.getPhoneNumber());
		return customerRepository.save(currentCustomer);
	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	public Customer findCustomerById(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		return optionalCustomer.get();
	}

	public Customer getByCustomerName(String customerName) {
		return customerRepository.findByCustomerName(customerName);

	}

	public Page<Customer> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Customer> pageCustomer = customerRepository.findAll(pageable);
		return pageCustomer;
	}
}
	

