package com.skilldistillery.payroll.services;

import java.util.List;

import com.skilldistillery.payroll.entities.Payroll;

public interface PayrollService {

	List<Payroll> index();

	Payroll getPayrollById(int id);

	void createPayroll(Payroll payroll);

	Payroll updatePayroll(Payroll pr, int id);

	void deleteEmployee(int id);

}
