package com.example.demoemployee.service;

import java.util.List;

import com.example.demoemployee.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee emp);

	void updateEmployee(Employee emp);

	boolean deleteEmployee(int id);

	Employee getEmployee(int id);
	
	List<Employee> getEmployee(String queryParam, String identifier);

	List<Employee> getAllEmployees();
}
