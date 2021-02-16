package com.mk.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mk.entity.EmployeeGrade;

@Repository
public interface EmployeeGradeRepository extends JpaRepository<EmployeeGrade, Integer> {
	
	@Query("FROM EmployeeGrade eg where eg.employee.id = :empId and eg.grade = :grade and eg.year = :year ")
	List<EmployeeGrade> findEmployeeByCriteria(@Param("empId") Integer empId, @Param("grade") String grade, @Param("year") Integer year);

}
