package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.SupportTicketsServices;

@RefreshScope
@Controller
@RequestMapping(value = "/supportTickets")
public class SupportTicketsController {
	
	@Autowired
	private SupportTicketsServices supportTicketsServices;
	
	//@GetMapping(value = "/showSupportTickets")

}
