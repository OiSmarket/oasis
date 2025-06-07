package com.example.oismarket;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double amount;

    private String status = "pending";

    private LocalDateTime createdAt = LocalDateTime.now();

    // Геттеры и сеттеры (можно сгенерировать в IDE)
}