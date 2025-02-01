package com.example.demo.controller;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Users;
import com.example.demo.services.UsersService;

@RefreshScope
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Endpoint to fetch all users
    @GetMapping(value = "/getAllUsers")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    // Endpoint to fetch a user by ID
    @GetMapping(value = "/getUser/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable int userId) {
        try {
            Users user = usersService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 // Endpoint to fetch a user by Mobile Number
    @GetMapping(value = "/getUserByMobile/{mobileNumber}")
    public ResponseEntity<Users> getUserByMobileNumber(@PathVariable String mobileNumber) {
        try {
            Users user = usersService.getUserByMobileNumber(mobileNumber); // Assuming such a service method exists
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to add a new user
    @PostMapping(value = "/addUser")
    public ResponseEntity<Void> addUser(@RequestBody Users user) {
        try {
            usersService.addUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to update an existing user
    @PutMapping(value = "/updateUser")
    public ResponseEntity<Void> updateUser(@RequestBody Users user) {
        try {
            usersService.getUserById(user.getUserId()); // Check if user exists
            usersService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to delete a user by ID
    @DeleteMapping(value = "/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        try {
            usersService.getUserById(userId); // Check if user exists
            usersService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/userLogin/{user}/{pwd}")
	public String login(@PathVariable String user,@PathVariable String pwd) {
		return usersService.login(user, pwd);
	}
}
