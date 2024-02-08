package com.sebastian.clientsappbackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.services.ClientService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable Long id) {

        return clientService.findById(id);

    }

    @PostMapping("/clients")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client saveClient(@Valid @RequestBody Client client) {

        return clientService.save(client);

    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client updateClient(@Valid @RequestBody Client client, @PathVariable long id) {
        Client dbClient = clientService.findById(id);

        dbClient.setFirstName(client.getFirstName() != null ? client.getFirstName() : dbClient.getFirstName());
        dbClient.setLastName(client.getLastName() != null ? client.getLastName() : dbClient.getLastName());
        dbClient.setEmail(client.getEmail() != null ? client.getEmail() : dbClient.getEmail());

        return clientService.save(dbClient);

    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteClientById(@PathVariable Long id) {

        clientService.delete(id);
    }

}
