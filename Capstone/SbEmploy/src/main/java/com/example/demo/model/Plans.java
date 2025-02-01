package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Plans {
	@Id
	private int planId;
	private String planName;
	private String planType;
	private String details;
	private double price;
	private int validity;
	
	
	
	

}
