package com.sebastian.clientsappbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {

        return (List<Client>) clientRepository.findAll();

    }

}
