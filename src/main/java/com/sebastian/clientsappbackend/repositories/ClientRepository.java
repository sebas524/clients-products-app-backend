package com.sebastian.clientsappbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.entities.Region;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("from Region")
    public List<Region> findAllRegions();

}
