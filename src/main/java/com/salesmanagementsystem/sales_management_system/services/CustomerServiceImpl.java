package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditCustomerParameters;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
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

    @Override
    @Transactional(readOnly = true)
    public boolean customerWithPhoneNumberExists(PhoneNumber phoneNumber) {
        return repository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByPhoneNumber(PhoneNumber phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> getCustomer(UUID customerId) {
        return repository.findById(customerId);
    }

    @Override
    @Transactional
    public Customer editCustomer(UUID customerId, EditCustomerParameters parameters) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException(customerId, "Cliente"));
        if (parameters.getVersion() != customer.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(Customer.class, customerId.toString());
        }

        parameters.update(customer);
        return customer;
    }

    @Override
    @Transactional
    public void deleteCustomer(UUID customerId) {
        repository.deleteById(customerId);
    }
}
