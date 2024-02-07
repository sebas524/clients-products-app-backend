package com.sebastian.clientsappbackend.services;

import java.util.List;

import com.sebastian.clientsappbackend.entities.Client;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);

}
