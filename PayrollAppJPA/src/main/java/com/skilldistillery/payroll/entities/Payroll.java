package com.skilldistillery.payroll.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Payroll {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String week;
	
	@Column(name="period_start")
	private Date periodStart;

	@Column(name="period_end")
	private Date periodEnd;
	
	@OneToMany(mappedBy="payroll")
	List<Record> listOfRecords;
	
	public Payroll() {
		
	}

	public Payroll(String week, Date periodStart, Date periodEnd) {
		super();
		this.week = week;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Date getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}

	public Date getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
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
		Payroll other = (Payroll) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payroll [id=" + id + ", week=" + week + ", periodStart=" + periodStart + ", periodEnd=" + periodEnd
				+ "]";
	}
	
	
	
}
