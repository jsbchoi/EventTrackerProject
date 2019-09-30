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
import com.skilldistillery.payroll.entities.Payroll;
import com.skilldistillery.payroll.entities.Record;
import com.skilldistillery.payroll.repositories.EmployeeRepository;
import com.skilldistillery.payroll.repositories.PayrollRepository;
import com.skilldistillery.payroll.services.RecordService;

@RestController
@RequestMapping("api")
public class RecordController {

	@Autowired
	private RecordService recService;
	@Autowired
	private PayrollRepository prRepo;
	@Autowired
	private EmployeeRepository empRepo;
	
	@GetMapping("records")
	public List<Record> index() {
		return recService.getAllRecords();
	}
	
	@GetMapping("payroll/{prId}/records")
	public List<Record> getRecordsByPayrollId(@PathVariable int prId, HttpServletResponse resp) {
		if (prRepo.findById(prId).isPresent()) {
			return recService.getRecordsByPayrollId(prId);
		} else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("employees/{empId}/records")
	public List<Record> getRecordsByEmployeeId(@PathVariable int empId, HttpServletResponse resp) {
		if (empRepo.findById(empId).isPresent()) {
			return recService.getRecordsByEmployeeId(empId);
		} else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("payroll/{prId}/{empId}/records")
	public Record addRecord(@RequestBody Record rec, @PathVariable int prId, @PathVariable int empId, HttpServletResponse resp, HttpServletRequest req) {
		try {
			rec.setPayroll(prRepo.findById(prId).get());
			rec.setEmployee(empRepo.findById(empId).get());
			recService.createRecord(rec);
			resp.setStatus(201);
			StringBuffer sb = req.getRequestURL();
			sb.append("/");
			sb.append(rec.getId());
			resp.setHeader("Location", sb.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
		return rec;
	}
	
	@PutMapping("employees/{prId}/{empId}/records/{id}")
	public Record updateRecord(@PathVariable int prId, @PathVariable int empId, @PathVariable int id, @RequestBody Record rec, HttpServletResponse resp) {
		Employee e = empRepo.findById(empId).get();
		Payroll p = prRepo.findById(prId).get();
		if ( e == null || p == null ) {
			resp.setStatus(404);
			return null;
		}
		rec.setPayroll(p);
		rec.setEmployee(e);
		rec.setId(id);
		recService.updateRecord(rec);
		return rec;
	}
	
	@DeleteMapping("records/{id}")
	public Boolean deleteRecord(@PathVariable int id, HttpServletResponse resp) {
		if (recService.getRecordById(id) == null) {
			resp.setStatus(404);
			return false;
		} else {
			recService.deleteRecord(id);
			resp.setStatus(204);
			return true;
		}
	}
	
}
