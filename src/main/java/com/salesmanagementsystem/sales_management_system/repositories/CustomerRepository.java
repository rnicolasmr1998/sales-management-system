package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
    Optional<Customer> findByPhoneNumber(PhoneNumber phoneNumber);
}
