package com.app.skins.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.skins.models.enums.Rarity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "skins") //Nome da tabela no Pstgre
@Data

public class Skins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marketName;

    @Column(nullable = false)
    private String weaponType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rarity rarity;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;


    @Column(nullable = false)
    @JsonProperty("floatValue")
    private Float floatValue;

    private Integer paintSeed; // Adicionando: Pattern do item

    @Column(unique = true)
    private String assetId; // Adicionado: ID real da Steam para este item único

    private String inspectLink; // Adicionado: Link para ver no jogo

    @Column(nullable = false, length = 1000)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @Column(nullable = false)
    private Integer weight = 100;

    @ManyToOne
    @JoinColumn(name = "case_id")
    @JsonBackReference
    private  Cases cases;
}
