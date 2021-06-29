package com.cts.controllar;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bean.Employee;
import com.cts.service.IEmployeeService;

@RestController 
@RequestMapping("employee")
public class HelloControllar {
	@Autowired
	private IEmployeeService eService;
	
	@GetMapping
	public List<Employee> allEmployee() {
		return eService.finalAllEmployee();
	}
	@GetMapping("/{eid}")
	public Employee findEmployee(@PathVariable("eid") int empid) {
		return eService.findEmployee(empid);
	}
	@PostMapping
	public List<Employee> createEmployee(@RequestBody Employee emp) {
		return eService.createEmployee(emp);
	}
	@DeleteMapping("/{empid}")
	public List<Employee> deleteEmployee(@PathVariable int empid) {
		return eService.deleteEmployee(empid);
	}
}
