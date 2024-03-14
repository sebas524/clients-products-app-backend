package com.sebastian.clientsappbackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clientsappbackend.entities.User;
import com.sebastian.clientsappbackend.services.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.findAll();

    }

    // @PostMapping("/users")
    // public ResponseEntity<?> createUser(@Valid @RequestBody User user,
    // BindingResult result) {
    // if (result.hasFieldErrors()) {
    // return myValidation(result);

    // }

    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    // }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user, BindingResult result) {

        return userService.save(user);
    }

    @PostMapping("/register")
    public User createUserNoAdmin(@Valid @RequestBody User user, BindingResult result) {

        user.setAdmin(false);

        return userService.save(user);
    }

    // private ResponseEntity<?> myValidation(BindingResult result) {
    // Map<String, String> errors = new HashMap<>();

    // result.getFieldErrors().forEach(err -> {

    // errors.put(err.getField(), "Value " + err.getField() + " " +
    // err.getDefaultMessage());

    // });

    // return ResponseEntity.badRequest().body(errors);

    // }

}
