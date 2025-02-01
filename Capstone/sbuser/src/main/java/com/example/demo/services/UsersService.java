package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repo.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Retrieve all users
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Search for a user by ID
    public Users getUserById(int userId) {
        return usersRepository.findById(userId).orElse(null); // Returns null if user not found
    }

    // Add a new user
    public void addUser(Users user) {
        usersRepository.save(user);
    }

    // Update an existing user
    public void updateUser(Users user) {
        usersRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(int userId) {
        usersRepository.deleteById(userId);
    }

	public Users getUserByMobileNumber(String mobileNumber) {
		return usersRepository.findByMobileNumber(mobileNumber);
	}
	
	public String login(String mobileNumber,String passwordHash) {
		long count=	usersRepository.countByMobileNumberAndPasswordHash(mobileNumber, passwordHash);
		String res ="";
		res+=count;
		return res;
	}
	
}
