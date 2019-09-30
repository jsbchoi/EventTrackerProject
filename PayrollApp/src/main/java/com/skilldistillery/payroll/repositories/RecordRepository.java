package com.skilldistillery.payroll.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.payroll.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {

	List<Record> findByPayrollId(int id);
	
	List<Record> findByEmployeeId(int id);
}
