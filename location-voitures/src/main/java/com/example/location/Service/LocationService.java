package com.example.location.Service;

import com.example.location.Repository.LocationRepository;
import com.example.location.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public Location save(Location location) {
        // Calcul automatique du montant total
        if (location.getVoiture() != null && location.getDateDebut() != null && location.getDateFin() != null) {
            long jours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateFin());
            if (jours <= 0) jours = 1;
            location.setMontantTotal(jours * location.getVoiture().getPrixJour());
        }
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    public List<Location> filtrer(String statut, LocalDate debut, LocalDate fin) {
        if (statut != null && !statut.isEmpty() && debut != null && fin != null) {
            return locationRepository.findByStatutAndDateDebutBetween(statut, debut, fin);
        }
        if (statut != null && !statut.isEmpty()) {
            return locationRepository.findByStatut(statut);
        }
        if (debut != null && fin != null) {
            return locationRepository.findByDateDebutBetween(debut, fin);
        }
        return locationRepository.findAll();
    }

    public void mettreAJourStatut(Long id, String statut) {
        Location l = getById(id);
        if (l != null) {
            l.setStatut(statut);
            locationRepository.save(l);
        }
    }

    public Double totalRevenus() {
        Double total = locationRepository.totalRevenus();
        return total != null ? total : 0.0;
    }

    public Map<String, Double> revenusParMarque() {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Object[] row : locationRepository.revenusParMarque()) {
            result.put((String) row[0], (Double) row[1]);
        }
        return result;
    }

    public long countTotal() {
        return locationRepository.count();
    }

    public long countByStatut(String statut) {
        return locationRepository.findByStatut(statut).size();
    }
}
