package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bean.Employee;


public class EmployeeDao {
	List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

}
