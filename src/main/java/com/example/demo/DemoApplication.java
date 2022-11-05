package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.EmployeeDetail;
import com.example.demo.entities.Permission;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private Customer customer;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommandLineRunner");
		
		if(userService.getByUserName("admin") == null){
			userService.createDefaultAdmin();
		}
		
//		Department dept = new Department();
//		dept.setName("IT");
//		
//		Permission adminPermission = new Permission();
//		adminPermission.setRole("ADMIN");
//		Permission managerPermission = new Permission();
//		managerPermission.setRole("MANAGER");
//		
//		List<Permission> permissions = new ArrayList<Permission>();
//		permissions.add(adminPermission);
//		permissions.add(managerPermission);
//		
//		Employee emp1 = new Employee();
//		emp1.setName("Hung");
//		emp1.setDepartment(dept);
//		emp1.setPermissions(permissions);
//		
//		employeeRepository.save(emp1);
		
//		Optional<Employee> employee2Optional = employeeRepository.findById(1);
//		Employee employee2 = employee2Optional.get();
//		Date mydob = new SimpleDateFormat("dd/MM/yyyy").parse("15/01/1998");
//		employee2.setDateOfBirth(new Date());
//		employeeRepository.save(employee2);
//		employeeRepository.delete(employee5);
		}
	}

