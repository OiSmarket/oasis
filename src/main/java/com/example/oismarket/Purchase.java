package com.example.oismarket;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "referrer_id")
    private User referrer;

    private Double amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Геттеры и сеттеры (можно сгенерировать в IDE)
}