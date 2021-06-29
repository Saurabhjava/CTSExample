package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.bean.Employee;
import com.dao.EmployeeDao;

@Service
public class EmployeeService {
	
	public List<Employee> getAllEmployee(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("Beans.xml");
		EmployeeDao eDao=(EmployeeDao)ctx.getBean("edao");
		return eDao.getEmployees();
	}
	public List<Employee> createEmployee(Employee emp){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("Beans.xml");
		EmployeeDao eDao=(EmployeeDao)ctx.getBean("edao");
		List<Employee> li=eDao.getEmployees();
		li.add(emp);
		return li;
	}
}
