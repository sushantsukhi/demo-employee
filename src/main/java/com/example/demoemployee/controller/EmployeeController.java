package com.example.demoemployee.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demoemployee.exception.CustomException;
import com.example.demoemployee.exception.ErrorResponse;
import com.example.demoemployee.model.Employee;
import com.example.demoemployee.service.EmployeeService;

@RestController
@RequestMapping("v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws Exception {
		// @RequestHeader(name = "X-COM-LOCATION", required = false) String

		Integer id = employeeService.getAllEmployees().size() + 1;
		employee.setId(id);

		Employee addedEmp = employeeService.addEmployee(employee);

		if (addedEmp == null) {
			throw new CustomException("Unable to add Employee..");
		}
		return new ResponseEntity<Employee>(addedEmp, HttpStatus.OK);
	}

	@PutMapping(path = "/employee", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) throws CustomException {
		Employee updatedEmp = employeeService.updateEmployee(emp);
		if (updatedEmp == null) {
			throw new CustomException("Employee to update doesn´t exist");
		}
		return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);
	}

	@DeleteMapping(path = "/employee")
	public ResponseEntity<ErrorResponse> deleteEmployee(@RequestParam String id) throws CustomException {
		boolean empDeletedFlag = employeeService.deleteEmployee(Integer.valueOf(id));
		if (!empDeletedFlag) {
			throw new CustomException("Employee doesn´t exist to delete with ID: " + id);
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.OK.value(), "Employee has been deleted"),
				HttpStatus.OK);
		// return "Employee deleted successfully";
	}

	// , headers = { "Content-Type=application/xml", "Accept=application/xml" }
	@GetMapping(path = "/employee")
	public List<Employee> findEmployee(@RequestParam("name") Optional<String> name,
			@RequestParam("dept") Optional<String> dept, @RequestParam("email") Optional<String> email)
			throws Exception {
		// Map<String, String> allParams,
		// @RequestParam List<String> id
		// @RequestHeader("accept-language") String lang,
		List<Employee> employees = null;
		String queryParamsAndID = findQueryParamsAndIDs(name, dept, email);
		if (queryParamsAndID != null) {
			String[] queryParamsAndIDArray = queryParamsAndID.split(";");
			employees = employeeService.getEmployee(queryParamsAndIDArray[0], queryParamsAndIDArray[1]);
		} else {
			throw new Exception("unknown query paramter passed..");
		}
		return employees;
	}

	// , headers = { "Content-Type=application/xml", "Accept=application/xml" }
	@GetMapping(path = "/employee/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int empId) throws CustomException {
		// @RequestHeader("accept-language") String lang,
		Employee employee = employeeService.getEmployee(empId);
		if (employee == null) {
			throw new CustomException("Employee does not exist with id: " + empId);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	private String findQueryParamsAndIDs(Optional<String> name, Optional<String> dept, Optional<String> email) {
		int counter = 0;
		String queryParams = null;
		String queryIds = null;

		if (name.isPresent()) {
			counter++;
			if (dept.isPresent()) {
				counter++;
			}
			if (email.isPresent()) {
				counter++;
				counter++;
			}
			if (counter == 1) {
				queryParams = name.get();
				queryIds = "name";
			}
			if (counter == 2) {
				queryParams = name.get() + "," + dept.get();
				queryIds = "name,dept";
			}
		} else if (dept.isPresent()) {
			counter++;
			if (email.isPresent()) {
				counter++;
			}
			if (name.isPresent()) {
				counter++;
				counter++;
			}
			if (counter == 1) {
				queryParams = dept.get();
				queryIds = "dept";
			}
			if (counter == 2) {
				queryParams = dept.get() + "," + email.get();
				queryIds = "dept,email";
			}
		} else if (email.isPresent()) {
			counter++;
			if (name.isPresent()) {
				counter++;
			}
			if (dept.isPresent()) {
				counter++;
				counter++;
			}
			if (counter == 1) {
				queryParams = email.get();
				queryIds = "email";
			}
			if (counter == 2) {
				queryParams = name.get() + "," + email.get();
				queryIds = "name,email";
			}
		}
		if (counter == 4) {
			queryParams = name.get() + "," + dept.get() + "," + email.get();
			queryIds = "name,dept,email";
		}
		if (queryParams == null) {
			return null;
		}
		return queryParams + ";" + queryIds;
	}
}
