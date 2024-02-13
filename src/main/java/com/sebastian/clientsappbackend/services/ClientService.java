package com.sebastian.clientsappbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clientsappbackend.entities.Client;

public interface ClientService {

    Page<Client> findAll(Pageable pageable);

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);

}
