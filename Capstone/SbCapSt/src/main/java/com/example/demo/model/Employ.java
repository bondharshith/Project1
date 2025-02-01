package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "employ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employ_id")
    private int employId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_joining", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;

    @Column(name = "address")
    private String address;

    @Column(name = "employ_user_name", nullable = false, unique = true)
    private String employUserName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    
    @Column(name = "employ_desig", nullable = false)
    private String EmployDesignation;
}
