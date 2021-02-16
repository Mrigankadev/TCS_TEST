package com.mk.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.dto.EmployeeDto;
import com.mk.dto.EmployeeGradeAddRequest;
import com.mk.entity.Employee;
import com.mk.entity.EmployeeGrade;
import com.mk.repository.EmployeeRepository;
import com.mk.repository.EmployeeGradeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeGradeRepository employeeGradeRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public EmployeeDto getEmployeeGrades(Integer empId, String grade, Integer year) {
		EmployeeDto employeeDto = new EmployeeDto();
		List<EmployeeGrade> employeeGrades = employeeGradeRepository.findEmployeeByCriteria(empId, grade, year);
		employeeDto.setEmpId(empId);
		if (employeeGrades != null) {
			Map<String, Integer> gradesMap = employeeGrades.stream()
					.collect(Collectors.toMap(EmployeeGrade::getGrade, EmployeeGrade::getYear));
			employeeDto.setGrades(gradesMap);
		}
		return employeeDto;
	}

	public void addEmployeeGrade(EmployeeGradeAddRequest employeeGradeAddRequest) {
		EmployeeGrade employeeGrade = new EmployeeGrade();
		Optional<Employee> emp = employeeRepository.findById(employeeGradeAddRequest.getEmpId());
		if(emp.isPresent()) {
			employeeGrade.setEmployee(emp.get());
		}
		employeeGrade.setGrade(employeeGradeAddRequest.getGrade());
		employeeGrade.setYear(employeeGradeAddRequest.getYear());
		employeeGradeRepository.saveAndFlush(employeeGrade);
	}

	public Optional<Employee> getEmployeeById(Integer id) {
		return employeeRepository.findById(id);
	}

}
