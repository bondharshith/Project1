package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Plans;
import com.example.demo.model.Transactions;
import com.example.demo.model.UserPlan;
import com.example.demo.repo.TransactionsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionsServices {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	public List<Transactions> showTransactions(){
		return transactionsRepository.findAll();
	}
	
	public Transactions searchById(int id) {
		return transactionsRepository.findById(id).get();
	}
	
	public void addTransactions(Transactions transactions) {
		transactionsRepository.save(transactions);
		
	}
	
	public Transactions searchByUserId(int userId) {
		return transactionsRepository.findByUserId(userId);
	}

}
