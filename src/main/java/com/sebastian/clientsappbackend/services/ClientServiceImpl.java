package com.sebastian.clientsappbackend.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.entities.Region;
import com.sebastian.clientsappbackend.exceptions.ResourceNotFoundException;
import com.sebastian.clientsappbackend.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private FileUploadService fileUploadService;

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    public ClientServiceImpl(ClientRepository clientRepository, FileUploadService fileUploadService) {
        this.clientRepository = clientRepository;
        this.fileUploadService = fileUploadService;

    }

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

    @Override
    public ResponseEntity<?> uploadImage(MultipartFile file, Long id) {

        Map<String, Object> response = new HashMap<>();

        Client client = findById(id);

        if (!file.isEmpty()) {

            String fileName = null;
            try {
                fileName = fileUploadService.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String previousFile = client.getImage();

            fileUploadService.delete(previousFile);

            client.setImage(fileName);

            save(client);

            response.put("client", client);
            response.put("message", "file has been successfully uploaded:" + fileName);

        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Resource> getImage(String imageName) {

        Resource resource = null;
        try {
            resource = fileUploadService.load(imageName);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        HttpHeaders header = new HttpHeaders();

        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {

        return clientRepository.findAllRegions();
    }

}
