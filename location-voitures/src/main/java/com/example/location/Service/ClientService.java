package com.example.location.Service;

import com.example.location.Repository.ClientRepository;
import com.example.location.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> rechercher(String nom) {
        if (nom != null && !nom.isEmpty()) {
            return clientRepository.findByNomContainingIgnoreCase(nom);
        }
        return clientRepository.findAll();
    }
}
