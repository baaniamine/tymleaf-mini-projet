package com.example.location.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "voitures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String immatriculation;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String segment; // citadine, berline, SUV, utilitaire...

    @Column(nullable = false)
    private boolean disponible;

    @Column(nullable = false)
    private Double prixJour;
}
