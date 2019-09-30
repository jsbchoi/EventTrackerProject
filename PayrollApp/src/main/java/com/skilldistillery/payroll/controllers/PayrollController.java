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

import com.skilldistillery.payroll.entities.Payroll;
import com.skilldistillery.payroll.services.PayrollService;

@RestController
@RequestMapping("api")
public class PayrollController {

	@Autowired
	private PayrollService prService;
	
	@GetMapping("payroll")
	public List<Payroll> getAllPayroll() {
		return prService.index();
	}
	
	@GetMapping("payroll/{id}")
	public Payroll getPayrollById(@PathVariable int id, HttpServletResponse resp) {
		Payroll pr = prService.getPayrollById(id);
		if (pr != null) {
			return pr;
		} else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("payroll")
	public Payroll createPayroll(@RequestBody Payroll payroll, HttpServletResponse resp, HttpServletRequest req) {
		try {
			prService.createPayroll(payroll);
			resp.setStatus(201);
			StringBuffer sb = req.getRequestURL();
			sb.append("/");
			sb.append(payroll.getId());
			resp.setHeader("Location", sb.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
		return payroll;
	}
	
	@PutMapping("payroll/{id}")
	public Payroll updatePayroll(@PathVariable int id, @RequestBody Payroll pr, HttpServletResponse resp) {
		if (prService.getPayrollById(id) == null) {
			resp.setStatus(404);
			return null;
		}
		return prService.updatePayroll(pr, id);
	}
	
	@DeleteMapping("payroll/{id}")
	public Boolean deletePayroll(@PathVariable int id, HttpServletResponse resp) {
		if (prService.getPayrollById(id) != null) {
			prService.deleteEmployee(id);
			resp.setStatus(204);
			return true;
		} else {
			resp.setStatus(404);
			return false;
		}
	}
}
