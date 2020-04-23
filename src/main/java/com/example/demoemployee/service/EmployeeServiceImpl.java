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
		employeeDao.addEmployee(emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		employeeDao.updateEmployee(emp);
	}

	@Override
	public boolean deleteEmployee(int id) {
		boolean deleted = employeeDao.deleteEmployee(id);
		return deleted;
	}

	@Override
	public Employee getEmployee(int id) {
		return employeeDao.getEmployee(id);
	}

	@Override
	public List<Employee> getEmployee(String queryParam, String identifier) {
		return employeeDao.getEmployee(queryParam, identifier);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

}
