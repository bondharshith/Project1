package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Plans;
import com.example.demo.repo.PlansRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlansService {
	
	@Autowired
	private PlansRepository plansRepository;
	
	public List<Plans> showPlans(){
		return plansRepository.findAll();
	}
	
	public Plans searchById(int id) {
		return plansRepository.findById(id).get();
	}
	
	public Plans searchByPlanName(String planName) {
		return plansRepository.findByPlanName(planName);
	}
	
	public void addPlans(Plans plan) {
		plansRepository.save(plan);
		
	}
	
	public void updatePlans(Plans plan) {
		plansRepository.save(plan);
	}
	public void deletePlans(int id) {
		plansRepository.deleteById(id);
	}
	

}
