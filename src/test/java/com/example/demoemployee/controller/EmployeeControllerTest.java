package com.example.demoemployee.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.demoemployee.model.Employee;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

	private RestTemplate restTemplate = new RestTemplate();
	private String baseUrl = "http://localhost:8888/v1/";

	@Test
	public void testGetEmployeeListSuccess() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employees");

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testAddEmployeeMissingHeader() throws URISyntaxException {
		URI uri = new URI(baseUrl + "employee");
		Employee employee = new Employee("Sushant", "IT", "abc@gmail.com");

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(201, result.getStatusCodeValue());
	}
}
