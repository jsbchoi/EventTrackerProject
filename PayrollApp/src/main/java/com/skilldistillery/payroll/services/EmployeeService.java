package com.skilldistillery.payroll.services;

import java.util.List;

import com.skilldistillery.payroll.entities.Employee;

public interface EmployeeService {

	List<Employee> index();

	Employee findById(int id);
	
	List<Employee> findByRole(String role);
	
	Employee createEmployee(Employee emp);

	Employee updateEmployee(Employee emp, int id);

	void deleteEmployee(int id);
}
