package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") // Link the entity to the "users" table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users{

    @Id
    @Column(name = "user_id")
    private int userId; 

    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "security_question", nullable = false)
    private String securityQuestion;

    @Column(name = "security_answer", nullable = false)
    private String securityAnswer;

    @Column(name = "address")
    private String address;

    @Column(name = "alternate_mobile_number")
    private String alternateMobileNumber;

    @Column(name = "account_status", nullable = false)
    private String accountStatus;
}
