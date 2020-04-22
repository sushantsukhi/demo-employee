package com.example.demoemployee.dao;

import java.util.List;

import com.example.demoemployee.model.Employee;

public interface EmployeeDao {

	void addEmployee(Employee emp);

	void updateEmployee(Employee emp);

	void deleteEmployee(int id);

	Employee getEmployee(int id);
	
	List<Employee> getAllEmployees();
}
