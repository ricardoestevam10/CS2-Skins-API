package com.app.skins.models;

import com.app.skins.models.enums.SkinsStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_skins")
@Data
public class UserSkins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // QUEM é o dono?
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // QUAL é a skin?
    @ManyToOne
    @JoinColumn(name = "skin_id", nullable = false)
    private Skins skin;

    @Column(name = "skin_name")
    private String skinName;

    @Enumerated(EnumType.STRING)
    private SkinsStatus status;

    private BigDecimal priceAtTime;



    private LocalDateTime acquiredAt;

    @PrePersist
    protected void onCreate() {
        acquiredAt = LocalDateTime.now();
    }
}