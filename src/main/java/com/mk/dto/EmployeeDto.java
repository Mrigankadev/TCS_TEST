package com.mk.dto;

import java.util.Map;

public class EmployeeDto {
	
	private Integer empId;
	private Map<String, Integer> grades;
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Map<String, Integer> getGrades() {
		return grades;
	}
	public void setGrades(Map<String, Integer> grades) {
		this.grades = grades;
	}
}
