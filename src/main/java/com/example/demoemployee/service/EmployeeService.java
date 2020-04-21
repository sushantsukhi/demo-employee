package com.example.demoemployee.service;

import java.util.Set;

import com.example.demoemployee.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee emp);

	void updateEmployee(Employee emp);

	void deleteEmployee(int id);

	Employee getEmployee(int id);

	Set<Employee> getAllEmployees();
}
