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

import com.example.demo.model.Billing;
import com.example.demo.services.BillingServices;

@RefreshScope
@RestController
@RequestMapping(value = "/billing")
public class BillingController {
	
	@Autowired
	private BillingServices billingServices;
	
	@GetMapping(value = "/showBilling")
	public List<Billing> showBilling(){
		return billingServices.showBilling();
	}
	
	@GetMapping(value = "/searchBilling/{id}")
	public ResponseEntity<Billing> get(@PathVariable int id){
		try {
			Billing billing = billingServices.searchById(id);
			return new ResponseEntity<Billing>(billing,HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<Billing>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/addBilling")
	public void addBilling(@RequestBody Billing billing) {
		billingServices.addBilling(billing);
	}
	
	@GetMapping(value = "/searchUser/{id}")
	public ResponseEntity<Billing> getByUser(@PathVariable int userId){
		try {
			Billing billing = billingServices.searchById(userId);
			return new ResponseEntity<Billing>(billing,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Billing>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/searchDueDate/{dueDate}")
	public ResponseEntity<Billing> getByUser(@PathVariable String dueDate){
		try {
			Billing billing = billingServices.searchByDueDate(dueDate);
			return new ResponseEntity<Billing>(billing,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Billing>(HttpStatus.NOT_FOUND);
		}
	}

}
