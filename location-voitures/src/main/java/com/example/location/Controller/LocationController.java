package com.example.location.Controller;

import com.example.location.Service.ClientService;
import com.example.location.Service.LocationService;
import com.example.location.Service.VoitureService;
import com.example.location.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private VoitureService voitureService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listeLocations(@RequestParam(required = false) String statut,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
                                 Model model) {
        model.addAttribute("locations", locationService.filtrer(statut, debut, fin));
        model.addAttribute("statut", statut);
        model.addAttribute("debut", debut);
        model.addAttribute("fin", fin);
        return "location/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("voitures", voitureService.filtrer(null, true));
        model.addAttribute("clients", clientService.getAll());
        return "location/form";
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute Location location,
                               @RequestParam Long voitureId,
                               @RequestParam Long clientId) {
        location.setVoiture(voitureService.getById(voitureId));
        location.setClient(clientService.getById(clientId));
        if (location.getStatut() == null || location.getStatut().isEmpty()) {
            location.setStatut("en cours");
        }
        locationService.save(location);
        // Marquer la voiture comme indisponible
        voitureService.setDisponibilite(voitureId, false);
        return "redirect:/locations";
    }

    @GetMapping("/edit/{id}")
    public String editLocation(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationService.getById(id));
        model.addAttribute("voitures", voitureService.getAll());
        model.addAttribute("clients", clientService.getAll());
        return "location/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
        return "redirect:/locations";
    }

    @GetMapping("/statut/{id}")
    public String mettreAJourStatut(@PathVariable Long id, @RequestParam String statut) {
        Location l = locationService.getById(id);
        locationService.mettreAJourStatut(id, statut);
        // Si terminée ou annulée, rendre la voiture disponible
        if ((statut.equals("terminée") || statut.equals("annulée")) && l != null && l.getVoiture() != null) {
            voitureService.setDisponibilite(l.getVoiture().getId(), true);
        }
        return "redirect:/locations";
    }
}
