package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions") // Link to "transactions" table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate transaction_id
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "plan_id", nullable = false)
    private int planId;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    @Column(name = "payment_mode", nullable = false)
    private String paymentMode;

    @Column(name = "transaction_timestamp", nullable = false, updatable = false)
    private LocalDateTime transactionTimestamp;

    // Automatically set the timestamp before persisting the entity
    @PrePersist
    protected void onCreate() {
        this.transactionTimestamp = LocalDateTime.now();
    }
}
