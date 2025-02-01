package com.example.demo.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.datausage;
import com.example.demo.services.Datausageservice;

@RestController
@RequestMapping(value="/datausage")
@CrossOrigin(origins = "http://localhost:3001")
public class DatausageController {
	@Autowired
	Datausageservice datausageservice;
	
	@GetMapping(value="/searchByusageId/{id}")
	public ResponseEntity<datausage> searchByusageId(@PathVariable int id){
		try {
				datausage data = datausageservice.findbyusageId(id);
				return new ResponseEntity<datausage>(data,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<datausage>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/searchByuserId/{id}")
	public ResponseEntity<datausage> searchByUserId(@PathVariable int id){
		try {
				datausage data = datausageservice.findByuserId(id);
				return new ResponseEntity<datausage>(data,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<datausage>(HttpStatus.NOT_FOUND);
		}
	}
}
