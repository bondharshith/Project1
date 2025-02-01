package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Billing;
import com.example.demo.model.Transactions;
import com.example.demo.repo.BillingRepository;

@Service
public class BillingServices {
	
	@Autowired
	private BillingRepository billingRepository;
	
	public List<Billing> showBilling(){
		return billingRepository.findAll();
	}
	
	public Billing searchById(int id) {
		return billingRepository.findById(id).get();
	}
	
	public void addBilling(Billing billing) {
		billingRepository.save(billing);
		
	}
	
	public Billing searchByUserId(int userId) {
		return billingRepository.findByUserId(userId);
	}
	
	public Billing searchByDueDate(String dueDate){
		return billingRepository.findByDueDate(dueDate);
	}

}
