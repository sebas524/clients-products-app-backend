package com.sebastian.clientsappbackend.controllers;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.clientsappbackend.entities.Client;
import com.sebastian.clientsappbackend.entities.Region;
import com.sebastian.clientsappbackend.services.ClientService;
import com.sebastian.clientsappbackend.services.FileUploadService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClientController {

    private ClientService clientService;
    private FileUploadService fileUploadService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients(Pageable pageable) {
        return clientService.findAll(pageable).toList();
    }

    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable Long id) {

        return clientService.findById(id);

    }

    @PostMapping("/clients")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client saveClient(@Valid @RequestBody Client client) {

        return clientService.save(client);

    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client updateClient(@Valid @RequestBody Client client, @PathVariable long id) {
        Client dbClient = clientService.findById(id);

        dbClient.setFirstName(client.getFirstName() != null ? client.getFirstName() : dbClient.getFirstName());
        dbClient.setLastName(client.getLastName() != null ? client.getLastName() : dbClient.getLastName());
        dbClient.setEmail(client.getEmail() != null ? client.getEmail() : dbClient.getEmail());
        dbClient.setRegion(client.getRegion() != null ? client.getRegion() : dbClient.getRegion());

        return clientService.save(dbClient);

    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteClientById(@PathVariable Long id) {

        Client client = clientService.findById(id);
        String previousImage = client.getImage();

        fileUploadService.delete(previousImage);
        clientService.delete(id);
    }

    @PostMapping("/clients/upload")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {

        return clientService.uploadImage(file, id);

    }

    @GetMapping("/clients/uploads/{imageName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {

        return clientService.getImage(imageName);

    }

    @GetMapping("/clients/regions")
    public List<Region> getAllRegions() {
        return clientService.findAllRegions();
    }

}
