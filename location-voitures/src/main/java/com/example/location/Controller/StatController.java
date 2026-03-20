package com.example.location.Controller;

import com.example.location.Service.ClientService;
import com.example.location.Service.LocationService;
import com.example.location.Service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatController {

    @Autowired
    private VoitureService voitureService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String statistiques(Model model) {
        long totalVoitures = voitureService.getAll().size();
        long voituresDisponibles = voitureService.countDisponibles();
        double tauxOccupation = totalVoitures > 0
                ? ((double)(totalVoitures - voituresDisponibles) / totalVoitures) * 100
                : 0;

        model.addAttribute("totalVoitures", totalVoitures);
        model.addAttribute("voituresDisponibles", voituresDisponibles);
        model.addAttribute("voituresIndisponibles", voitureService.countIndisponibles());
        model.addAttribute("tauxOccupation", String.format("%.1f", tauxOccupation));
        model.addAttribute("totalLocations", locationService.countTotal());
        model.addAttribute("locationsEnCours", locationService.countByStatut("en cours"));
        model.addAttribute("locationsTerminees", locationService.countByStatut("terminée"));
        model.addAttribute("totalClients", clientService.getAll().size());
        model.addAttribute("totalRevenus", String.format("%.2f", locationService.totalRevenus()));
        model.addAttribute("revenusParMarque", locationService.revenusParMarque());

        return "stats/dashboard";
    }
}
