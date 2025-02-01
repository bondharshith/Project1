package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.JwtEmployService;
import com.example.demo.model.AuthRequest;
import com.example.demo.model.Employ;
import com.example.demo.services.EmployService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3001")
public class EmployController {

    @Autowired
    private EmployService employService;

    @Autowired
    private JwtEmployService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewEmploy")
    public String addNewEmploy(@RequestBody Employ employ) {
        return employService.addEmploy(employ);
    }

    @GetMapping("/employ/employProfile")
    @PreAuthorize("hasAuthority('ROLE_EMPLOY')")
    public String employProfile() {
        return "Welcome to Employ Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid employ request!");
        }
    }

    @GetMapping("/showEmploy")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public List<Employ> showEmploy() {
        return employService.showEmploy();
    }

    @GetMapping("/searchEmploy/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Employ> searchEmploy(@PathVariable int id) {
        try {
            Employ employ = employService.searchEmploy(id);
            return new ResponseEntity<>(employ, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEmploy")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateEmploy(@RequestBody Employ employ) {
        try {
            String result = employService.updateEmploy(employ);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEmploy/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEmploy(@PathVariable int id) {
        try {
            String result = employService.deleteEmploy(id);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}