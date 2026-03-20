package com.example.location.Service;

import com.example.location.Repository.VoitureRepository;
import com.example.location.entity.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    public List<Voiture> getAll() {
        return voitureRepository.findAll();
    }

    public Voiture getById(Long id) {
        return voitureRepository.findById(id).orElse(null);
    }

    public Voiture save(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public void delete(Long id) {
        voitureRepository.deleteById(id);
    }

    public List<Voiture> filtrer(String segment, Boolean disponible) {
        if (segment != null && !segment.isEmpty() && disponible != null) {
            return voitureRepository.findBySegmentAndDisponible(segment, disponible);
        }
        if (segment != null && !segment.isEmpty()) {
            return voitureRepository.findBySegment(segment);
        }
        if (disponible != null) {
            return voitureRepository.findByDisponible(disponible);
        }
        return voitureRepository.findAll();
    }

    public void setDisponibilite(Long id, boolean disponible) {
        Voiture v = getById(id);
        if (v != null) {
            v.setDisponible(disponible);
            voitureRepository.save(v);
        }
    }

    public long countDisponibles() {
        return voitureRepository.countByDisponible(true);
    }

    public long countIndisponibles() {
        return voitureRepository.countByDisponible(false);
    }
}
