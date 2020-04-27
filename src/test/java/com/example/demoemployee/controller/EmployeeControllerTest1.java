package com.example.demoemployee.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demoemployee.exception.CustomException;
import com.example.demoemployee.model.Employee;
import com.example.demoemployee.service.EmployeeService;

public class EmployeeControllerTest1 {

	@InjectMocks
	EmployeeController controller;

	@Mock
	EmployeeService service;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllEmployeesTest() {
		List<Employee> list = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "Sushant", "Dev", "sushant@gmail.com");
		Employee emp2 = new Employee(2, "Siyan", "IT", "siyan@gmail.com");
		Employee emp3 = new Employee(3, "Prashant", "HR", "prashant@gmail.com");
		Employee emp4 = new Employee(4, "Sushant", "IT", "sushant1@gmail.com");
		Employee emp5 = new Employee(5, "Nitin", "DevOps", "nitin@gmail.com");
		Employee emp6 = new Employee(6, "Suyog", "Dev", "suyog@gmail.com");
		Employee emp7 = new Employee(7, "Sushant", "IT", "sushant2@gmail.com");

		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
		list.add(emp4);
		list.add(emp5);
		list.add(emp6);
		list.add(emp7);

		when(service.getAllEmployees()).thenReturn(list);

		// test
		ResponseEntity<List<Employee>> empList = controller.getAllEmployees();

		assertEquals(7, empList.getBody().size());
		verify(service, times(1)).getAllEmployees();
	}

	@Test
	public void getEmployeeByIdTest() throws CustomException {
		Employee employee = new Employee(2, "Siyan", "IT", "siyan@gmail.com");
		when(service.getEmployee(2)).thenReturn(employee);

		ResponseEntity<Employee> response = controller.getEmployee(2);

		Employee emp = response.getBody();

		assertEquals("Siyan", emp.getName());
		assertEquals("IT", emp.getDept());
		assertEquals("siyan@gmail.com", emp.getEmail());
		verify(service, times(1)).getEmployee(2);
	}

	@Test
	public void getEmployeeByNameTest() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "Sushant", "Dev", "sushant@gmail.com");
		Employee emp4 = new Employee(4, "Sushant", "IT", "sushant1@gmail.com");
		Employee emp7 = new Employee(7, "Sushant", "IT", "sushant2@gmail.com");

		list.add(emp1);
		list.add(emp4);
		list.add(emp7);

		when(service.getEmployee("sushant", "name")).thenReturn(list);

		ResponseEntity<List<Employee>> response = controller.findEmployee(Optional.of("sushant"), Optional.empty(),
				Optional.empty());

		assertEquals(3, response.getBody().size());
		verify(service, times(1)).getEmployee("sushant", "name");
	}

	@Test
	public void getEmployeeByDeptTest() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		Employee emp2 = new Employee(2, "Siyan", "IT", "siyan@gmail.com");
		Employee emp4 = new Employee(4, "Sushant", "IT", "sushant1@gmail.com");

		list.add(emp2);
		list.add(emp4);

		when(service.getEmployee("it", "dept")).thenReturn(list);

		ResponseEntity<List<Employee>> response = controller.findEmployee(Optional.empty(), Optional.of("it"),
				Optional.empty());

		assertEquals(2, response.getBody().size());
		verify(service, times(1)).getEmployee("it", "dept");
	}

	@Test
	public void getEmployeeByEmailTest() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		Employee emp6 = new Employee(6, "Suyog", "Dev", "suyog@gmail.com");
		list.add(emp6);

		when(service.getEmployee("suyog@gmail.com", "email")).thenReturn(list);

		ResponseEntity<List<Employee>> response = controller.findEmployee(Optional.empty(), Optional.empty(),
				Optional.of("suyog@gmail.com"));

		assertEquals(1, response.getBody().size());
		verify(service, times(1)).getEmployee("suyog@gmail.com", "email");
	}

	@Test
	public void addEmployeeTest() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Employee employee = new Employee("Siyan", "IT", "siyan@gmail.com");
		when(service.addEmployee(employee)).thenReturn(employee);

		ResponseEntity<Employee> response = controller.addEmployee(employee);

		System.out.println(response);
		Employee emp = response.getBody();
		assertEquals("Siyan", emp.getName());
		verify(service, times(1)).addEmployee(emp);
	}

	@Test
	public void updateEmployeeTest() throws Exception {
		Employee employee = new Employee(9, "Siyan", "IT", "siyan@gmail.com");
		when(service.updateEmployee(employee)).thenReturn(employee);

		ResponseEntity<Employee> response = controller.updateEmployee(employee);

		System.out.println(response);
		Employee emp = response.getBody();
		assertEquals("Siyan", emp.getName());
		verify(service, times(1)).updateEmployee(emp);
	}

}
