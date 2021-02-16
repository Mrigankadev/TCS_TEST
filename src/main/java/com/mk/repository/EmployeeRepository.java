package com.mk.repository;

import org.springframework.stereotype.Repository;

import com.mk.entity.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAll();
	Optional<Employee> findById(Integer id);
}
