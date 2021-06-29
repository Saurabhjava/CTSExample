package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.bean.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
