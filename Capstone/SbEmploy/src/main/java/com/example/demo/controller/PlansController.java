package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Plans;
import com.example.demo.services.PlansService;

@RestController
@RequestMapping(value="/plans")
@CrossOrigin(origins = "http://localhost:3001")
public class PlansController {
	
	@Autowired
	private PlansService plansService;
	
	@GetMapping(value="/showPlans")
	public List<Plans> showPlan(){
		return plansService.showPlans();
	}
	
	@GetMapping(value="/searchPlans/{id}")
	public ResponseEntity<Plans> get(@PathVariable int id){
		try {
			Plans plans =  plansService.searchById(id);
			return new ResponseEntity<Plans>(plans,HttpStatus.OK);
		}catch(NoSuchElementException e){
			return new ResponseEntity<Plans>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/searchPlanName/{plan}")
	public ResponseEntity<Plans> getByPlan(@PathVariable String plan) {
		try {
			Plans plans =  plansService.searchByPlanName(plan);
			return new ResponseEntity<Plans>(plans,HttpStatus.OK);
			
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Plans>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addPlans")
	public void addPlan(@RequestBody Plans plan) {
		plansService.addPlans(plan);
	}
	

	
	@PutMapping(value="/updatePlans")
	public  ResponseEntity<Plans> updatePlans(@RequestBody Plans plan){
		try {
			plansService.searchById(plan.getPlanId());
			plansService.updatePlans(plan);
			return new ResponseEntity<Plans>(HttpStatus.OK);
			
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Plans>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/deletePlan/{id}")
	public  ResponseEntity<Plans> deletePlan(@PathVariable int id,@RequestBody Plans plan){
		try {
			plansService.searchById(plan.getPlanId());
			plansService.deletePlans(id);
			return new ResponseEntity<Plans>(HttpStatus.OK);
			
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Plans>(HttpStatus.NOT_FOUND);
		}
	}
	

}
