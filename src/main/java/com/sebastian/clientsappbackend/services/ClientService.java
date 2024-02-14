package com.sebastian.clientsappbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.clientsappbackend.entities.Client;

public interface ClientService {

    Page<Client> findAll(Pageable pageable);

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);

    ResponseEntity<?> uploadImage(MultipartFile file, Long id);

    void deleteClientPrevImage(Client client);

    ResponseEntity<Resource> getImage(String imageName);
}
