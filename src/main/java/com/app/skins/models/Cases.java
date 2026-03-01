package com.app.skins.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cases")
@Data
public class Cases {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 1000)
    private  String imageUrl;

    private String description;

    @Column(nullable = false)
    private  String category;


    @OneToMany(mappedBy = "cases")
    @ToString.Exclude
    @JsonManagedReference
    private List<Skins> skins;
}
