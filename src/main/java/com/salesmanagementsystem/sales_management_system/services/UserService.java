package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditUserParameters;
import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.parameters.CreateUserParameters;

public interface UserService {
    User createUser(CreateUserParameters parameters);
    ImmutableSet<User> getAllUsers();
    Page<User> getUsers(Pageable pageable);
    boolean userWithEmailExists(Email email);
    Optional<User> getUser(UUID userId);
    User editUser(UUID userId, EditUserParameters parameters);
    void deleteUser(UUID userId);
    Optional<User> findByEmail(Email email);
}
