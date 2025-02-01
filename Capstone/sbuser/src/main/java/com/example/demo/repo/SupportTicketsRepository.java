package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SupportTickets;
import java.util.List;


public interface SupportTicketsRepository extends JpaRepository<SupportTickets, Integer>{
	SupportTickets findByUserId(int userId);
	SupportTickets findByEmployId(int employId);
	List<SupportTickets> findByStatus(String status);
}
