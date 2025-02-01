package com.example.demo.controller;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Transactions;
import com.example.demo.services.TransactionsServices;

@RestController
@RefreshScope
@RequestMapping(value = "/transactions")
public class TrasactionsController {
	
	@Autowired
	private TransactionsServices transactionsServices;
	
	@GetMapping(value = "/showTrasactions")
	public List<Transactions> showTransactions(){
		return transactionsServices.showTransactions();
	}
	
	@GetMapping(value = "/searchTransactions/{id}")
	public ResponseEntity<Transactions> get(@PathVariable int id){
		try {
			Transactions transactions = transactionsServices.searchById(id);
			return new ResponseEntity<Transactions>(transactions,HttpStatus.OK);
		}catch(NoSuchElementException e){
			return new ResponseEntity<Transactions>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/addTransactions")
	public void addTransactions(@RequestBody Transactions transactions) {
		transactionsServices.addTransactions(transactions);
	}
	
	@GetMapping(value = "/searchUser/{id}")
	public ResponseEntity<Transactions> getByUser(@PathVariable int userId){
		try {
			Transactions transactions = transactionsServices.searchById(userId);
			return new ResponseEntity<Transactions>(transactions,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Transactions>(HttpStatus.NOT_FOUND);
		}
	}

}
