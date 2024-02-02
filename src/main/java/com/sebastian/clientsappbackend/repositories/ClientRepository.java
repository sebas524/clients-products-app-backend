package com.sebastian.clientsappbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebastian.clientsappbackend.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
