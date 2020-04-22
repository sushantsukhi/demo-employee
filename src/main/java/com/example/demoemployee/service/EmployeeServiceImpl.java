package com.example.demoemployee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoemployee.dao.EmployeeDao;
import com.example.demoemployee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public void addEmployee(Employee emp) {
		System.out.println("Inside EmployeeServiceImpl: addEmployee..");
		employeeDao.addEmployee(emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		System.out.println("Inside EmployeeServiceImpl: updateEmployee..");
		employeeDao.updateEmployee(emp);
	}

	@Override
	public void deleteEmployee(int id) {
		System.out.println("Inside EmployeeServiceImpl: deleteEmployee..");
		employeeDao.deleteEmployee(id);
	}

	@Override
	public Employee getEmployee(int id) {
		System.out.println("Inside EmployeeServiceImpl: getEmployee..");
		return employeeDao.getEmployee(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("Inside EmployeeServiceImpl: getAllEmployees..");
		return employeeDao.getAllEmployees();
	}

}
