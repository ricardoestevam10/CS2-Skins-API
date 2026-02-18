package com.ricardo.skins.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type;
    private LocalDateTime timestamp;
    private String descriptions;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
