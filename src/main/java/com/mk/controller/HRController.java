package com.mk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.EmployeeDto;
import com.mk.dto.EmployeeGradeAddRequest;
import com.mk.entity.Employee;
import com.mk.service.EmployeeService;

@RestController
public class HRController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	private ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = null;
		try {
			employees = employeeService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/employee/{empId}/{grade}/{year}")
	private ResponseEntity<EmployeeDto> getEmployeeGrades(@PathVariable("empId") Integer empId,
			@PathVariable("grade") String grade, @PathVariable("year") Integer year) {
		EmployeeDto employeeDto = null;
		try {
			employeeDto = employeeService.getEmployeeGrades(empId, grade, year);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employeeDto, HttpStatus.OK);
	}

	@PutMapping("/employee")
	private ResponseEntity<Employee> addEmployeeGrade(@RequestBody EmployeeGradeAddRequest request) {
		Employee employee = null;
		try {
			employeeService.addEmployeeGrade(request);
			if(employeeService.getEmployeeById(request.getEmpId()).isPresent()) {
				employee = employeeService.getEmployeeById(request.getEmpId()).get();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	

}
