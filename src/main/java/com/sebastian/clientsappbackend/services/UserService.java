package com.sebastian.clientsappbackend.services;

import java.util.List;

import com.sebastian.clientsappbackend.entities.User;

public interface UserService {

    List<User> findAll();

    User save(User user);

}
