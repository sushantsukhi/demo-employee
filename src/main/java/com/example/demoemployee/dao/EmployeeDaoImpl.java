package com.example.demoemployee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demoemployee.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static Map<Integer, Employee> map = new HashMap<Integer, Employee>();

	static {
		map.put(1, new Employee(1, "Sushant", "Dev", "abc@gmail.com"));
		map.put(2, new Employee(2, "Siyan", "IT", "xyz@gmail.com"));
	}

	@Override
	public void addEmployee(Employee emp) {
		System.out.println("EmployeeDaoImpl: addEmployee started..");
		map.put(emp.getId(), emp);
		System.out.println("EmployeeDaoImpl: addEmployee ended..");
	}

	@Override
	public void updateEmployee(Employee emp) {
		System.out.println("EmployeeDaoImpl: updateEmployee started..");
		map.put(emp.getId(), emp);
		System.out.println("EmployeeDaoImpl: updateEmployee ended..");
	}

	@Override
	public void deleteEmployee(int id) {
		System.out.println("EmployeeDaoImpl: deleteEmployee started..");
		map.remove(id);
		System.out.println("EmployeeDaoImpl: deleteEmployee ended..");
	}

	@Override
	public Employee getEmployee(int id) {
		System.out.println("EmployeeDaoImpl: getEmployee started..");
		Employee employee = map.get(id);
		System.out.println("EmployeeDaoImpl: getEmployee ended..");
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("EmployeeDaoImpl: getAllEmployees started..");
		List<Employee> empList = new ArrayList<Employee>(map.values());
		System.out.println("EmployeeDaoImpl: getAllEmployees ended..");
		return empList;
	}

}
