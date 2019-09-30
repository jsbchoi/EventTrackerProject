package com.skilldistillery.payroll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.payroll.entities.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

}
