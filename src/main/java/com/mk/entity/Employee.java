package com.mk.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "experience")
	private Integer experience;
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeGrade> employeeGrade;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public List<EmployeeGrade> getEmployeeGrade() {
		return employeeGrade;
	}

	public void setEmployeeGrade(List<EmployeeGrade> employeeGrade) {
		this.employeeGrade = employeeGrade;
	}
	
	
}
