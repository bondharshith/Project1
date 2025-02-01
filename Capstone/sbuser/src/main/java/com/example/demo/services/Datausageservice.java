package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.datausage;
import com.example.demo.repo.DataUsageRepository;

@Service
public class Datausageservice {
	@Autowired
	DataUsageRepository datausagerepository;
	
	public datausage findbyusageId(int usageId) {
		return datausagerepository.findById(usageId).get();
	}
	public datausage findByuserId(int userId) {
		return datausagerepository.findByUserId(userId);
	}

}
