package com.example.location.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    private Double montantTotal;

    @Column(nullable = false)
    private String statut; // en cours, terminée, annulée

    @ManyToOne
    @JoinColumn(name = "voiture_id", nullable = false)
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
