package com.sebastian.clientsappbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.exceptions.ResourceNotFoundException;
import com.sebastian.clientsappbackend.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // @Override
    // public List<Client> findAll() {

    // return (List<Client>) clientRepository.findAll();

    // }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client findById(Long id) {

        Optional<Client> dbClient = clientRepository.findById(id);

        if (dbClient.isEmpty()) {
            throw new ResourceNotFoundException("Client id provided not found.");

        }

        return dbClient.get();
    }

    @Override
    public Client save(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {

        Client dbClient = findById(id);

        clientRepository.deleteById(dbClient.getId());
    }

}
