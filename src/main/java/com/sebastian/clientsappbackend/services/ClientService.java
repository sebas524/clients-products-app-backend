package com.sebastian.clientsappbackend.services;

import java.util.List;

import com.sebastian.clientsappbackend.entities.Client;

public interface ClientService {

    List<Client> findAll();

}
