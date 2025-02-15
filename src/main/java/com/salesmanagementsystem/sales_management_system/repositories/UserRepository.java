package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(Email email);
}
