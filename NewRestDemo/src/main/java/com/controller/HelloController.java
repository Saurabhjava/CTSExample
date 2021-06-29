package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Employee;
import com.service.EmployeeService;


@RestController
public class HelloController {
	@Autowired
	EmployeeService eService;
	@GetMapping("/hello")
	public List<Employee> sayHello() {
		return eService.getAllEmployee();
	}
	@PostMapping("/create")
	public List<Employee> saveEmployee(@RequestBody Employee emp) {
		return eService.createEmployee(emp);
	}
}
