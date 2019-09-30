package com.skilldistillery.payroll.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.payroll.entities.Employee;
import com.skilldistillery.payroll.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> index() {
		return empRepo.findAll();
	}
	
	@Override
	public Employee findById(int id) {
		Optional<Employee> optEmp = empRepo.findById(id);
		if (optEmp.isPresent()) {
			return optEmp.get();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Employee> findByRole(String role) {
		return empRepo.findByRole(role);
	}

	@Override
	public Employee createEmployee(Employee emp) {
		empRepo.saveAndFlush(emp);
		return emp;
	}
	
	@Override
	public Employee updateEmployee(Employee emp, int id) {
		emp.setId(id);
		empRepo.saveAndFlush(emp);
		return emp;
	}

	@Override
	public void deleteEmployee(int id) {
		empRepo.deleteById(id);
	}

	
	

}
