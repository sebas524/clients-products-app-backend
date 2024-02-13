package com.sebastian.clientsappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clientsappbackend.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
