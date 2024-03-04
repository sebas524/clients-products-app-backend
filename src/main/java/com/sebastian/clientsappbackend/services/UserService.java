package com.sebastian.clientsappbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sebastian.clientsappbackend.entities.User;

public interface UserService {

    List<User> findAll();

    User save(User user);

}
