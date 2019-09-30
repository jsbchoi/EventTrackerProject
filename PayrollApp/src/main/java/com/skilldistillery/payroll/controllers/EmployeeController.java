package com.skilldistillery.payroll.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.payroll.entities.Employee;
import com.skilldistillery.payroll.services.EmployeeServiceImpl;

@RestController
@RequestMapping("api")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl empService;
	
	@GetMapping("employees")
	public List<Employee> getEmployees() {
		return empService.index();
	}
	
	@GetMapping("employees/{id}")
	public Employee getEmployeeById(@PathVariable int id, HttpServletResponse resp) {
		Employee e = empService.findById(id);
		if (e != null) {
			return e;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("employees/search/{role}")
	public List<Employee> getEmployeesByRole(@PathVariable String role) {
		return empService.findByRole(role);
	}
	
	@PostMapping("employees")
	public Employee addPost(@RequestBody Employee emp, HttpServletResponse resp, HttpServletRequest req) {
		try {
			empService.createEmployee(emp);
			resp.setStatus(201);
			StringBuffer sb = req.getRequestURL();
			sb.append(emp.getId());
			resp.setHeader("Location", sb.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
		return emp;
	}
	
	@PutMapping("employees/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee emp, HttpServletResponse resp) {
		if (empService.findById(id) == null) {
			resp.setStatus(404);
			return null;
		}
		return empService.updateEmployee(emp, id);
	}
	
	@DeleteMapping("employees/{id}")
	public Boolean deleteEmployee(@PathVariable int id, HttpServletResponse resp) {
		if (empService.findById(id) != null) {
			empService.deleteEmployee(id);
			resp.setStatus(204);
			return true;
		} else {
			resp.setStatus(404);
			return false;
		}
	}
	
}
