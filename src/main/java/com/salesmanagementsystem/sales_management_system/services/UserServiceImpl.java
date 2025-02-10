package com.salesmanagementsystem.sales_management_system.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.parameters.CreateUserParameters;
import com.salesmanagementsystem.sales_management_system.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User createUser(CreateUserParameters parameters) {
        User user = new User(parameters.getFullName(),
                parameters.getGender(),
                parameters.getBirthday(),
                parameters.getEmail(),
                parameters.getPhoneNumber());
        user.setUserStatus(true);
        user.setRegistrationDate(LocalDate.now());
        user.setLastUpdateDate(LocalDate.now());
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public ImmutableSet<User> getAllUsers() {
        return ImmutableSet.copyOf(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
