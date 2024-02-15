package com.sebastian.clientsappbackend.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.exceptions.ResourceNotFoundException;
import com.sebastian.clientsappbackend.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

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

    @Override
    public ResponseEntity<?> uploadImage(MultipartFile file, Long id) {

        Map<String, Object> response = new HashMap<>();

        Client client = findById(id);

        if (!file.isEmpty()) {

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");

            Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();

            log.info(filePath.toString());

            try {
                Files.copy(file.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            deleteClientPrevImage(client);

            client.setImage(fileName);

            save(client);

            response.put("client", client);
            response.put("message", "file has been successfully uploaded:" + fileName);

        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public void deleteClientPrevImage(Client client) {

        String previousImageName = client.getImage();

        if (previousImageName != null && previousImageName.length() > 0) {
            Path previousImagePath = Paths.get("uploads").resolve(previousImageName).toAbsolutePath();
            File previosImageFile = previousImagePath.toFile();
            if (previosImageFile.exists() && previosImageFile.canRead()) {
                previosImageFile.delete();

            }

        }

    }

    @Override
    public ResponseEntity<Resource> getImage(String imageName) {

        Path filePath = Paths.get("uploads").resolve(imageName).toAbsolutePath();

        log.info(filePath.toString());

        Resource resource = null;

        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!resource.exists() && !resource.isReadable()) {

            // * so if image where to be deleted, the no pic image will show:
            filePath = Paths.get("src/main/resources/static/images").resolve("no-pic.png").toAbsolutePath();

            try {
                resource = new UrlResource(filePath.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            log.error("image could not be loaded." + imageName);

        }

        HttpHeaders header = new HttpHeaders();

        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);

    }

}
