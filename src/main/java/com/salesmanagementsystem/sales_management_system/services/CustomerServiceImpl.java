package com.salesmanagementsystem.sales_management_system.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.parameters.CreateCustomerParameters;
import com.salesmanagementsystem.sales_management_system.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Customer createCustomer(CreateCustomerParameters parameters) {
        Customer customer = new Customer(parameters.getFullName(),
                parameters.getGender(),
                parameters.getPhoneNumber(),
                parameters.getDebt());
        customer.setCustomerStatus(true);
        customer.setRegistrationDate(LocalDate.now());
        customer.setLastUpdateDate(LocalDate.now());
        return repository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public ImmutableSet<Customer> getAllCustomers() {
        return ImmutableSet.copyOf(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> getCustomers(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
