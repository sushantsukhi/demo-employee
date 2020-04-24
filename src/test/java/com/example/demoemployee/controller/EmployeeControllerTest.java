package com.example.demoemployee.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demoemployee.model.Employee;
import com.example.demoemployee.service.EmployeeService;

//@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	private RestTemplate restTemplate = new RestTemplate();
	private String baseUrl = "http://localhost:8888/v1/";

	// @Test
	public void testGetEmployeeListSuccess() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employees");

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	// @Test
	public void testAddEmployeeMissingHeader() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employee");
		Employee employee = new Employee("Sushant", "IT", "abc@gmail.com");

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(201, result.getStatusCodeValue());
	}

	// @Test
	public void testAddEmployee() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		// when(employeeService.addEmployee(any(Employee.class))).thenReturn(true);

		Employee employee = new Employee(1, "Sushant", "Dev", "sushant@gmail.com");
		ResponseEntity<Employee> responseEntity = employeeController.addEmployee(employee);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
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

		when(employeeService.getAllEmployees()).thenReturn(values);

		// when
		//List<Employee> allEmployees = employeeController.getAllEmployees();

		// then
		//assertThat(allEmployees.size()).isEqualTo(2);
		// This is to verify how many times employeeService is called
		// verify(employeeService, times(1)).getAllEmployees();

		//assertThat(allEmployees.get(0).getName()).isEqualTo(employee1.getName());
		//assertThat(allEmployees.get(1).getName()).isEqualTo(employee2.getName());

	}
}
