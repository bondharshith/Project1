package com.example.demo.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Billing") // Link the entity to the "users" table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Billing {
	
	@Id
	private int billId;
    private int userId;
    private double charges;
    private double payments;
    private String dueDate;


}
