package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditUserParameters;
import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
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

    @Override
    @Transactional(readOnly = true)
    public boolean userWithEmailExists(Email email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(Email email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUser(UUID userId) {
        return repository.findById(userId);
    }

    @Override
    @Transactional(readOnly = false)
    public User editUser(UUID userId, EditUserParameters parameters) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(userId, "Usuario"));
        if (parameters.getVersion() != user.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(User.class, userId.toString());
        }
        
        parameters.update(user);
        return user;
    }

    @Override
    public void deleteUser(UUID userId) {
        repository.deleteById(userId);
    }
}
