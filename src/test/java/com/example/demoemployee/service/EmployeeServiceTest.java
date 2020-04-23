package com.example.demoemployee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demoemployee.dao.EmployeeDao;
import com.example.demoemployee.model.Employee;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeDao employeeDao;

	// @Test
	public void testAddEmployee() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		// when(employeeService.addEmployee(any(Employee.class))).thenReturn(true);

		Employee employee = new Employee(1, "Sushant", "Dev", "sushant@gmail.com");
		// employeeServiceImpl.addEmployee(employee);

		// assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		// assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	}

	@Test
	public void testFindAll() {
		// given
		Employee employee1 = new Employee(1, "Sushant", "Dev", "abc@gmail.com");
		Employee employee2 = new Employee(2, "Siyan", "IT", "xyz@gmail.com");
		// Employees employees = new Employees();
		Map<Integer, Employee> map = new HashMap<Integer, Employee>();
		map.put(1, employee1);
		map.put(2, employee2);
		List<Employee> values = new ArrayList<Employee>(map.values());

		when(employeeDao.getAllEmployees()).thenReturn(values);

		// when
		List<Employee> allEmployees = employeeServiceImpl.getAllEmployees();

		// then
		assertThat(allEmployees.size()).isEqualTo(2);
		// This is to verify how many times employeeService is called
		// verify(employeeService, times(1)).getAllEmployees();

		assertThat(allEmployees.get(0).getName()).isEqualTo(employee1.getName());
		assertThat(allEmployees.get(1).getName()).isEqualTo(employee2.getName());
	}

	@Test
	public void testGetEmployee() {
		// given
		Employee employee1 = new Employee(1, "Sushant", "Dev", "sushant@gmail.com");
		Employee employee2 = new Employee(4, "Sushant", "IT", "sushant1@gmail.com");
		Employee employee3 = new Employee(7, "Sushant", "IT", "sushant2@gmail.com");

		// Employees employees = new Employees();
		Map<Integer, Employee> map = new HashMap<Integer, Employee>();
		map.put(4, employee2);
		map.put(7, employee3);
		List<Employee> values = new ArrayList<Employee>(map.values());

		when(employeeDao.getEmployee("name,IT", "Sushant")).thenReturn(values);

		// when
		List<Employee> allEmployees = employeeServiceImpl.getEmployee("name,IT", "Sushant");

		System.out.println("Employee size: " + allEmployees.size());
		// then
		assertThat(allEmployees.size()).isEqualTo(2);
		// This is to verify how many times employeeService is called
		// verify(employeeService, times(1)).getAllEmployees();

		assertThat(allEmployees.get(0).getName()).isEqualTo(employee1.getName());
		assertThat(allEmployees.get(1).getName()).isEqualTo(employee2.getName());
	}

}
