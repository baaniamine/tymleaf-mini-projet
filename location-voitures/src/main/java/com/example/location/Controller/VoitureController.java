package com.example.location.Controller;

import com.example.location.Service.VoitureService;
import com.example.location.entity.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @GetMapping
    public String listeVoitures(@RequestParam(required = false) String segment,
                                @RequestParam(required = false) Boolean disponible,
                                Model model) {
        model.addAttribute("voitures", voitureService.filtrer(segment, disponible));
        model.addAttribute("segment", segment);
        model.addAttribute("disponible", disponible);
        return "voiture/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("voiture", new Voiture());
        return "voiture/form";
    }

    @PostMapping("/save")
    public String saveVoiture(@ModelAttribute Voiture voiture) {
        voitureService.save(voiture);
        return "redirect:/voitures";
    }

    @GetMapping("/edit/{id}")
    public String editVoiture(@PathVariable Long id, Model model) {
        model.addAttribute("voiture", voitureService.getById(id));
        return "voiture/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteVoiture(@PathVariable Long id) {
        voitureService.delete(id);
        return "redirect:/voitures";
    }

    @GetMapping("/disponibilite/{id}")
    public String toggleDisponibilite(@PathVariable Long id, @RequestParam boolean valeur) {
        voitureService.setDisponibilite(id, valeur);
        return "redirect:/voitures";
    }
}
