package com.skilldistillery.payroll.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String role;

	@Column(name="base_salary")
	private String baseSalary;

	@Column(name="hourly_rate")
	private String hourlyRate;

	@Column(name="loan_amount")
	private String loanAmount;
	
	@JsonIgnore
	@OneToMany(mappedBy="employee")
	private List<Record> listOfRecords;
	
	public Employee() {
		
	}
	
	public Employee(String name, String role, String baseSalary, String hourlyRate, String loanAmount) {
		super();
		this.name = name;
		this.role = role;
		this.baseSalary = baseSalary;
		this.hourlyRate = hourlyRate;
		this.loanAmount = loanAmount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public List<Record> getListOfRecords() {
		return listOfRecords;
	}

	public void setListOfRecords(List<Record> listOfRecords) {
		this.listOfRecords = listOfRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", role=" + role + ", baseSalary=" + baseSalary
				+ ", hourlyRate=" + hourlyRate + ", loanAmount=" + loanAmount + "]";
	}

	
	
}
