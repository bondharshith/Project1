package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserPlan;

public interface UserPlanRepository extends JpaRepository<UserPlan, Integer>{
	UserPlan findByUserId(int userId);
	
}
