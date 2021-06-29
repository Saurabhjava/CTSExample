package com.cts.service;

import java.util.List;

import com.cts.bean.Employee;

public interface IEmployeeService {
	List<Employee> finalAllEmployee();
	List<Employee> createEmployee(Employee emp);
	Employee findEmployee(int empid);
	List<Employee> updateEmployee(Employee emp);
	List<Employee> deleteEmployee(int empid);
	
}
