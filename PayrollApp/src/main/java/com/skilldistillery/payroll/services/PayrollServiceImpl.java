package com.skilldistillery.payroll.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.payroll.entities.Payroll;
import com.skilldistillery.payroll.repositories.PayrollRepository;

@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
	private PayrollRepository prRepo;
	
	@Override
	public List<Payroll> index() {
		return prRepo.findAll();
	}

	@Override
	public Payroll getPayrollById(int id) {
		Optional<Payroll> pr = prRepo.findById(id);
		if (pr.isPresent()) {
			return pr.get();
		} else {
			return null;
		}
	
	}

	@Override
	public void createPayroll(Payroll payroll) {
		prRepo.saveAndFlush(payroll);
	}

	@Override
	public Payroll updatePayroll(Payroll pr, int id) {
		pr.setId(id);
		prRepo.saveAndFlush(pr);
		return pr;
	}

	@Override
	public void deleteEmployee(int id) {
		prRepo.deleteById(id);
	}
}
