package com.example.oismarket;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "student";

    @Column(unique = true)
    private String refCode;

    private Double balance = 0.0;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "referrer")
    private List<Purchase> referrals;

    @OneToMany(mappedBy = "user")
    private List<Payout> payouts;

    // Геттеры и сеттеры (можно сгенерировать в IDE)

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}
}

// Добавь такие же методы для других полей, если потребуется