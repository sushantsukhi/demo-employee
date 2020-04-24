package com.example.demoemployee.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demoemployee.interceptor.NoLogging;
import com.example.demoemployee.model.Employee;

@NoLogging
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static Map<Integer, Employee> map = new HashMap<Integer, Employee>();
	private static final String NAME = "name";
	private static final String DEPT = "dept";
	private static final String EMAIL = "email";

	static {
		map.put(1, new Employee(1, "Sushant", "Dev", "sushant@gmail.com"));
		map.put(2, new Employee(2, "Siyan", "IT", "siyan@gmail.com"));
		map.put(3, new Employee(3, "Prashant", "HR", "prashant@gmail.com"));
		map.put(4, new Employee(4, "Sushant", "IT", "sushant1@gmail.com"));
		map.put(5, new Employee(5, "Nitin", "DevOps", "nitin@gmail.com"));
		map.put(6, new Employee(6, "Suyog", "Dev", "suyog@gmail.com"));
		map.put(7, new Employee(7, "Sushant", "IT", "sushant2@gmail.com"));
	}

	@Override
	public Employee addEmployee(Employee emp) {
		Employee addedEmp = map.put(emp.getId(), emp);
		return addedEmp;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Employee updatedEmp = map.put(emp.getId(), emp);
		return updatedEmp;
	}

	@Override
	public boolean deleteEmployee(int id) {
		boolean removed = map.entrySet().removeIf(emp -> (emp.getKey() == id));
		return removed;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		Optional<Employee> optionalEmp = map.entrySet().stream().filter(emp -> (emp.getKey() == id))
				.map(emp -> emp.getValue()).findAny();
		if (optionalEmp.isPresent()) {
			employee = optionalEmp.get();
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployee(String queryParam, String identifier) {
		String[] queryParamArray = queryParam.split(",");
		String[] identifierArray = identifier.split(",");

		List<Employee> empList = null;
		if (identifierArray.length == 1) {
			if (NAME.equalsIgnoreCase(identifierArray[0])) {
				empList = map.entrySet().stream()
						.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getName()))
						.map(emp -> emp.getValue()).collect(Collectors.toList());
			} else if (DEPT.equalsIgnoreCase(identifierArray[0])) {
				empList = map.entrySet().stream()
						.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getDept()))
						.map(emp -> emp.getValue()).collect(Collectors.toList());
			} else if (EMAIL.equalsIgnoreCase(identifierArray[0])) {
				empList = map.entrySet().stream()
						.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getEmail()))
						.map(emp -> emp.getValue()).collect(Collectors.toList());
			}
		} else if (identifierArray.length == 2) {
			if (NAME.equalsIgnoreCase(identifierArray[0]) && DEPT.equalsIgnoreCase(identifierArray[1])) {
				empList = map.entrySet().stream()
						.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getName()))
						.filter(emp -> queryParamArray[1].equalsIgnoreCase(emp.getValue().getDept()))
						.map(emp -> emp.getValue()).collect(Collectors.toList());
			} else if (NAME.equalsIgnoreCase(identifierArray[0]) && EMAIL.equalsIgnoreCase(identifierArray[1])) {
				empList = map.entrySet().stream()
						.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getName()))
						.filter(emp -> queryParamArray[1].equalsIgnoreCase(emp.getValue().getEmail()))
						.map(emp -> emp.getValue()).collect(Collectors.toList());
			} else if (DEPT.equalsIgnoreCase(identifierArray[0]) && EMAIL.equalsIgnoreCase(identifierArray[1])) {
				empList = map.entrySet().stream()
						.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getDept()))
						.filter(emp -> queryParamArray[1].equalsIgnoreCase(emp.getValue().getEmail()))
						.map(emp -> emp.getValue()).collect(Collectors.toList());
			}
		} else if (identifierArray.length == 3) {
			empList = map.entrySet().stream()
					.filter(emp -> queryParamArray[0].equalsIgnoreCase(emp.getValue().getName()))
					.filter(emp -> queryParamArray[1].equalsIgnoreCase(emp.getValue().getDept()))
					.filter(emp -> queryParamArray[2].equalsIgnoreCase(emp.getValue().getEmail()))
					.map(emp -> emp.getValue()).collect(Collectors.toList());
		}
		return empList;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>(map.values());
		return empList;
	}

}
