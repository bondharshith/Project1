package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.SupportTickets;
import com.example.demo.repo.SupportTicketsRepository;

@Service
public class SupportTicketsServices {
	
	@Autowired
	private SupportTicketsRepository supportTicketRepository;
	
	public List<SupportTickets> showSupportTickets(){
		return supportTicketRepository.findAll();
	}
	
	public SupportTickets searchById(int id) {
		return supportTicketRepository.findById(id).get();
	}
	
	public void addSupportTickets(SupportTickets supportTickets) {
		supportTicketRepository.save(supportTickets);
	}
	
	public SupportTickets searchByUserId(int userId) {
		return supportTicketRepository.findByUserId(userId);
	}
	
	public SupportTickets searchByEmployId(int employId) {
		return supportTicketRepository.findByEmployId(employId);
	}
	
	public List<SupportTickets> findByStatus(String status){
		return supportTicketRepository.findByStatus(status);
	}

}
