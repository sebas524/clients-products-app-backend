package com.sebastian.clientsappbackend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebastian.clientsappbackend.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
