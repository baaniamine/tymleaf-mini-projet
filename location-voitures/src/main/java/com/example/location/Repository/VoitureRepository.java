package com.example.location.Repository;

import com.example.location.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    List<Voiture> findBySegment(String segment);
    List<Voiture> findByDisponible(boolean disponible);
    List<Voiture> findBySegmentAndDisponible(String segment, boolean disponible);
    long countByDisponible(boolean disponible);
}
