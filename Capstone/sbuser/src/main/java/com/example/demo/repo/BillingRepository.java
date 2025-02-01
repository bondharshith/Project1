package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Billing;
import java.util.List;


public interface BillingRepository extends JpaRepository<Billing, Integer>{
	Billing findByUserId(int userId);
	Billing findByDueDate(String dueDate);

}
