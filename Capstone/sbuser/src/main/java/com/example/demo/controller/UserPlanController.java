package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserPlan;
import com.example.demo.services.UserPlanService;

@RefreshScope
@RestController
@RequestMapping(value = "/userplan")
public class UserPlanController {

	@Autowired
	private UserPlanService userPlanService;
	
	@GetMapping(value = "/showUserplan")
	public List<UserPlan> showUserPlan(){
		return userPlanService.showUserPlan();
	}
	
	@GetMapping(value = "searchUserplan/{id}")
	public ResponseEntity<UserPlan> get(@PathVariable int id){
		try {
			UserPlan userplan = userPlanService.searchById(id);
			return new ResponseEntity<UserPlan>(userplan,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<UserPlan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/searchUser/{id}")
	public ResponseEntity<UserPlan> getByUser(@PathVariable int userId){
		try {
			UserPlan userplan = userPlanService.searchById(userId);
			return new ResponseEntity<UserPlan>(userplan,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<UserPlan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/addUserplan")
	public void addUserPlan(@RequestBody UserPlan userplan) {
		userPlanService.addUserPlan(userplan);
	}
	
	@PutMapping(value = "/updateUserplan")
	public ResponseEntity<UserPlan> updateUserPlan(@RequestBody UserPlan userPlan){
		try {
			userPlanService.searchById(userPlan.getPlanId());
			userPlanService.updateUserPlan(userPlan);
			return new ResponseEntity<UserPlan>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<UserPlan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/deleteUserplan/{id}")
	public ResponseEntity<UserPlan> deleteUserPlan(@PathVariable int id, @RequestBody UserPlan userPlan){
		try {
			userPlanService.searchById(userPlan.getPlanId());
			userPlanService.deleteUserPlan(id);
			return new ResponseEntity<UserPlan>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<UserPlan>(HttpStatus.NOT_FOUND);
		}
		
	}
}
