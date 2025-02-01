package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByMobileNumber(String mobileNumber);
    long countByMobileNumberAndPasswordHash(String mobileNumber,String passwordHash);
}