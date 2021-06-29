package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bean.Employee;
import com.cts.dao.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	IEmployeeRepository repo;

	@Override
	public List<Employee> finalAllEmployee() {
		return repo.findAll();
	}

	@Override
	public List<Employee> createEmployee(Employee emp) {
		repo.saveAndFlush(emp);
		return finalAllEmployee();
	}

	@Override
	public Employee findEmployee(int empid) {
		Optional<Employee> op=repo.findById(empid);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}

	@Override
	public List<Employee> updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> deleteEmployee(int empid) {
		Employee emp=findEmployee(empid);
		if(emp!=null) {
			repo.delete(emp);
		}
		return finalAllEmployee();
	}

}
