package com.skilldistillery.payroll.services;

import java.util.List;

import com.skilldistillery.payroll.entities.Record;

public interface RecordService {

	List<Record> getAllRecords();

	List<Record> getRecordsByPayrollId(int prId);

	List<Record> getRecordsByEmployeeId(int empId);

	void createRecord(Record rec);

	void updateRecord(Record rec);

	Record getRecordById(int id);

	void deleteRecord(int id);

}
