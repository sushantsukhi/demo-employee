package com.example.demoemployee.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

	@Test
	public void testGetEmployeeListSuccess() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employees");

		ResponseEntity<Employee[]> response = restTemplate.getForEntity(uri, Employee[].class);
		List<Employee> asList = Arrays.asList(response.getBody());

		Assert.assertEquals(200, response.getStatusCodeValue());
		// Assert.assertEquals(8, asList.size());
	}

	@Test
	public void testAddEmployeeWithFailure() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employee");
		Employee employee = new Employee("Ashes", "IT", "abc@gmail.com");

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

		try {
			restTemplate.postForEntity(uri, request, Employee.class);
		} catch (HttpClientErrorException e) {
			Assert.assertEquals(406, e.getRawStatusCode());
		}
	}

	@Test
	public void testAddEmployeeWithSuccess() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employee");
		Employee employee = new Employee("Ashes", "IT", "abc@gmail.com");

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

		ResponseEntity<Employee> result = restTemplate.postForEntity(uri, request, Employee.class);
		System.out.println(result.getStatusCodeValue());
		Assert.assertEquals(201, result.getStatusCodeValue());
	}

}
