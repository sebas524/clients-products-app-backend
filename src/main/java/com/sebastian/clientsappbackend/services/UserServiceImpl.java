package com.sebastian.clientsappbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clientsappbackend.entities.Role;
import com.sebastian.clientsappbackend.entities.User;
import com.sebastian.clientsappbackend.repositories.RoleRepository;
import com.sebastian.clientsappbackend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {

        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(User user) {

        // * create array to store roles of user:
        List<Role> userRoles = new ArrayList<>();

        // * find role:
        Optional<Role> dbRoleUser = roleRepository.findByName("ROLE_USER");

        // * ADD ROLE_USER to roles array:
        dbRoleUser.ifPresent(foundRole -> userRoles.add(foundRole));

        if (user.isAdmin()) {

            Optional<Role> dbRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            dbRoleAdmin.ifPresent((foundRole -> userRoles.add(foundRole)));

        }

        user.setRoles(userRoles);

        // ! PASSWORD ENCRYPTION
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

}
