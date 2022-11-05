package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;

@Controller
@RequestMapping(value = "/admin")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public String showCustomerList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize = 3;
		Page<Customer> pageCustomer = customerService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Customer> customers = pageCustomer.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageCustomer.getTotalPages());
		model.addAttribute("totalItems", pageCustomer.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("customers", customers);
		return "customers";
	}

	@GetMapping("/addcustomer")
	public String showSignUpForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "add-customer";
	}

	@GetMapping("/editcustomer")
	public String showEditForm(@RequestParam(name = "customerId") Long id, Model model) {
		Customer customer = customerService.findCustomerById(id);
		model.addAttribute("customer", customer);
		return "edit-customer";
	}
	
	@GetMapping("/deletecustomer")
	public String deleteCustomer(@RequestParam(name = "customerId") Long id) {
		customerService.deleteCustomer(id);
		return "redirect:/admin/customers";
	}

	@PostMapping("/addcustomer")
	public String addCustomer(@Valid Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-customer";
		}		
		customerService.saveCustomer(customer);

		return "redirect:/admin/customers";
	}

	@PostMapping("/updatecustomer")
	public String updateCustomer(@RequestParam(name = "customerId") Long id, @Valid Customer customer, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			customer.setId(id);
			return "edit-customer";
		}	
		customerService.updateCustomer(customer, id);
		return "redirect:/admin/customers";
	}
}
