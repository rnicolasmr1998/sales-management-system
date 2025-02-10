package com.salesmanagementsystem.sales_management_system.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreateSupplierParameters;
import com.salesmanagementsystem.sales_management_system.repositories.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;

    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Supplier createSupplier(CreateSupplierParameters parameters) {
        Supplier supplier = new Supplier(parameters.getRuc(),
                parameters.getSupplierName(),
                parameters.getPhoneNumber(),
                parameters.getSupplierDebtSoles(),
                parameters.getSupplierDebtDollars());
        supplier.setSupplierStatus(true);
        supplier.setRegistrationDate(LocalDate.now());
        supplier.setLastUpdateDate(LocalDate.now());
        return repository.save(supplier);
    }

    @Override
    @Transactional(readOnly = true)
    public ImmutableSet<Supplier> getAllSuppliers() {
        return ImmutableSet.copyOf(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Supplier> getSuppliers(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
