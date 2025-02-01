package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions , Integer>{
	Transactions findByUserId(int userId);

}
