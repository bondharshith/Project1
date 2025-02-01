package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserPlan;
import com.example.demo.repo.UserPlanRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserPlanService {
	
	@Autowired
	private UserPlanRepository userPlanRepository;
	
	public List<UserPlan> showUserPlan(){
		return userPlanRepository.findAll();
	}
	
	public UserPlan searchById(int id) {
		return userPlanRepository.findById(id).get();
	}
	
	public UserPlan searchByUserId(int userId) {
		return userPlanRepository.findByUserId(userId);
	}
	
	public void addUserPlan(UserPlan userPlan) {
		userPlanRepository.save(userPlan);
	}
	
	public void updateUserPlan(UserPlan userPlan) {
		userPlanRepository.save(userPlan);
	}
	
	public void deleteUserPlan(int id) {
		userPlanRepository.deleteById(id);
	}
}
