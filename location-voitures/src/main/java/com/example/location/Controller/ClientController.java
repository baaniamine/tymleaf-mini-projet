package com.example.location.Controller;

import com.example.location.Service.ClientService;
import com.example.location.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listeClients(@RequestParam(required = false) String nom, Model model) {
        model.addAttribute("clients", clientService.rechercher(nom));
        model.addAttribute("nom", nom);
        return "client/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/form";
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getById(id));
        return "client/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}
