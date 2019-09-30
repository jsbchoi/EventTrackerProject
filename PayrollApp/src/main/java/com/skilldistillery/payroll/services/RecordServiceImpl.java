package com.skilldistillery.payroll.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.payroll.entities.Record;
import com.skilldistillery.payroll.repositories.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordRepository recRepo;

	@Override
	public List<Record> getAllRecords() {
		return recRepo.findAll();
	}

	@Override
	public List<Record> getRecordsByPayrollId(int prId) {
		return recRepo.findByPayrollId(prId);
	}

	@Override
	public List<Record> getRecordsByEmployeeId(int empId) {
		return recRepo.findByEmployeeId(empId);
	}

	@Override
	public void createRecord(Record rec) {
		recRepo.saveAndFlush(rec);
	}

	@Override
	public void updateRecord(Record rec) {
		recRepo.saveAndFlush(rec);
	}

	@Override
	public Record getRecordById(int id) {
		Optional<Record> r = recRepo.findById(id);
		if (r.get() == null) {
			return null;
		} else {
			return r.get();
		}
	}

	@Override
	public void deleteRecord(int id) {
		recRepo.deleteById(id);
	}
	
	
}
