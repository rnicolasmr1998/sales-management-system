package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditCustomerParameters;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.parameters.CreateCustomerParameters;

public interface CustomerService {
    Customer createCustomer(CreateCustomerParameters parameters);
    ImmutableSet<Customer> getAllCustomers();
    Page<Customer> getCustomers(Pageable pageable);
    boolean customerWithPhoneNumberExists(PhoneNumber phoneNumber);
    Optional<Customer> getCustomer(UUID customerId);
    Customer editCustomer(UUID customerId, EditCustomerParameters parameters);
    void deleteCustomer(UUID customerId);
    Optional<Customer> findByPhoneNumber(PhoneNumber phoneNumber);
}
