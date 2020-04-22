package com.example.demoemployee.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demoemployee.model.Employee;
import com.example.demoemployee.service.EmployeeService;

@RestController
@RequestMapping("v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) throws Exception {
		// @RequestHeader(name = "X-COM-LOCATION", required = false) String
		// headerLocation
		// Generate resource id
		System.out.println("EmployeeController: addEmployee started..");
		Integer id = employeeService.getAllEmployees().size() + 1;
		employee.setId(id);

		employeeService.addEmployee(employee);

		// Create resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId())
				.toUri();
		System.out.println("EmployeeController: addEmployee ended..");

		// Send location in response
		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = "/employee", consumes = MediaType.APPLICATION_XML_VALUE)
	public void updateEmployee(@RequestBody Employee emp) {
		employeeService.updateEmployee(emp);
		System.out.println("Employee Updated");
	}

	@DeleteMapping(path = "/employee")
	public String deleteEmployee(@RequestParam String id) {
		try {
			employeeService.deleteEmployee(Integer.valueOf(id));
			System.out.println("Employee Deleted");
			return "Employee deleted successfully";
		} catch (NumberFormatException nfe) {
			System.out.println(nfe);
			return "Unable to delete employee successfully";
		}
	}

	// , headers = { "Content-Type=application/xml", "Accept=application/xml" }
	@GetMapping(path = "/employee/{empId}")
	public Employee getEmployee(@PathVariable int empId) {
		// @RequestHeader("accept-language") String lang,
		// System.out.println("Language: " + lang);
		Employee employee = employeeService.getEmployee(empId);
		System.out.println("Employee: " + employee);
		return employee;
	}

	@GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		System.out.println("EmployeeController: getAllEmployees started..");

		List<Employee> employees = employeeService.getAllEmployees();
		System.out.println("Employees count: " + employees.size());
		System.out.println("Employees: " + employees);
		System.out.println("EmployeeController: getAllEmployees ended..");

		return employees;
	}
}
