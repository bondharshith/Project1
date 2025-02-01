package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SupportTickets") // Link the entity to the "users" table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupportTickets {
	
	@Id
	private int ticketId;
    private int userId;
    private int employId;
    private String text;
    private String status;
    private String createdAt;
    private String updatedAt;


}
