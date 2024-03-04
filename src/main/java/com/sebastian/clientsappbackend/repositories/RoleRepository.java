package com.sebastian.clientsappbackend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sebastian.clientsappbackend.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String Name);

}
