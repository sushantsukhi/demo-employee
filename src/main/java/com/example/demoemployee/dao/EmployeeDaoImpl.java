package com.example.demoemployee.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.demoemployee.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static Map<Integer, Employee> map = new HashMap<Integer, Employee>();
	
	static {
		map.put(1, new Employee(1, "Sushant", "Dev", "abc@gmail.com"));
		map.put(2, new Employee(2, "Siyan", "IT",  "xyz@gmail.com"));
	}

	@Override
	public void addEmployee(Employee emp) {
		map.put(emp.getId(), emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		map.put(emp.getId(), emp);
	}

	@Override
	public void deleteEmployee(int id) {
		map.remove(id);
	}

	@Override
	public Employee getEmployee(int id) {
		return map.get(id);
	}

	@Override
	public Set<Employee> getAllEmployees() {
		Collection<Employee> emps = map.values();
		Set<Employee> values = new HashSet<Employee>(emps);
		return values;
	}

}
