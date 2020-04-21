package com.example.demoemployee.service;

import java.util.Set;

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
		employeeDao.addEmployee(emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		employeeDao.updateEmployee(emp);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeDao.deleteEmployee(id);
	}

	@Override
	public Employee getEmployee(int id) {
		return employeeDao.getEmployee(id);
	}

	@Override
	public Set<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

}
