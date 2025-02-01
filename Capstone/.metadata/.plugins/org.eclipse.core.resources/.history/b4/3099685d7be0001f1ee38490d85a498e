package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Users;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    // Endpoint to add a user to the database
    @PostMapping("/dbAddUser")
    public String dbAddUser(@RequestBody Users user) {
        // Make the POST request
        String url = "http://SBCAPST/users/addUser"; // Replace with the actual service URL
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    // Endpoint to fetch all users from the database
    @GetMapping("/dbUsers")
    public Object[] showDbUsers() {
        Object[] users = restTemplate.getForObject("http://SBCAPST/users/getAllUsers", Object[].class);
        return users;
    }

    // Endpoint to fetch all users from another service (e.g., Eureka client)
    @GetMapping("/interUsers")
    public Object[] showInterUsers() {
        Object[] users = restTemplate.getForObject("http://SBCAPST/users/findall", Object[].class);
        return users;
    }
}