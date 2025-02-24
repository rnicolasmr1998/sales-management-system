package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditSupplierParameters;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
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

    @Override
    @Transactional(readOnly = true)
    public boolean supplierWithPhoneNumberExists(PhoneNumber phoneNumber) {
        return repository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean supplierWithRucExists(String ruc) {
        return repository.existsByRuc(ruc);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean supplierWithSupplierNameExists(String supplierName) {
        return repository.existsBySupplierName(supplierName);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplier> getSupplier(UUID supplierId) {
        return repository.findById(supplierId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplier> findByRuc(String ruc) {
        return repository.findByRuc(ruc);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplier> findByPhoneNumber(PhoneNumber phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplier> findBySupplierName(String supplierName) {
        return repository.findBySupplierName(supplierName);
    }

    @Override
    @Transactional
    public Supplier editSupplier(UUID supplierId, EditSupplierParameters parameters) {
        Supplier supplier = repository.findById(supplierId)
                .orElseThrow(() -> new ObjectNotFoundException(supplierId, "Proveedor"));
        if (parameters.getVersion() != supplier.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(Supplier.class, supplierId.toString());
        }
        
        parameters.update(supplier);
        return supplier;
    }

    @Override
    @Transactional
    public void deleteSupplier(UUID supplierId) {
        repository.deleteById(supplierId);
    }
}
