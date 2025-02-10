package com.salesmanagementsystem.sales_management_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.parameters.CreateCustomerParameters;

public interface CustomerService {
    Customer createCustomer(CreateCustomerParameters parameters);
    ImmutableSet<Customer> getAllCustomers();
    Page<Customer> getCustomers(Pageable pageable);
}
