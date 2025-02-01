package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.datausage;

public interface DataUsageRepository extends JpaRepository<datausage, Integer> {
	datausage findByuserId(int userId);

}
