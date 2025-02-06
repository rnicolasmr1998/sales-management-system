package com.salesmanagementsystem.sales_management_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.forms.CreateUserParameters;

public interface UserService {
    User createUser(CreateUserParameters parameters);
    ImmutableSet<User> getAllUsers();
    Page<User> getUsers(Pageable pageable);
}
