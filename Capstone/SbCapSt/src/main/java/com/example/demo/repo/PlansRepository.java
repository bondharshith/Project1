package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Plans;

public interface PlansRepository extends JpaRepository<Plans, Integer> {
	 Plans findByPlanName(String planName);

}
