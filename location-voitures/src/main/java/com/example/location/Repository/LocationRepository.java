package com.example.location.Repository;

import com.example.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByStatut(String statut);
    List<Location> findByDateDebutBetween(LocalDate debut, LocalDate fin);
    List<Location> findByStatutAndDateDebutBetween(String statut, LocalDate debut, LocalDate fin);

    @Query("SELECT SUM(l.montantTotal) FROM Location l WHERE l.statut = 'terminée'")
    Double totalRevenus();

    @Query("SELECT l.voiture.marque, SUM(l.montantTotal) FROM Location l GROUP BY l.voiture.marque")
    List<Object[]> revenusParMarque();
}
