package com.sebastian.clientsappbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.services.ClientService;

@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

}
