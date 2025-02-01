package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Transactions") // Link the entity to the "users" table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
	
	@Id
	private int transactionId;
    private int userId;
    private int planId;
    private String transactionStatus;
    private String paymentMode;
    private String transactionTimestamp;

}
