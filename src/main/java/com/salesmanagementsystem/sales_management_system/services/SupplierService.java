package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditSupplierParameters;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreateSupplierParameters;

public interface SupplierService {
    Supplier createSupplier(CreateSupplierParameters parameters);
    ImmutableSet<Supplier> getAllSuppliers();
    Page<Supplier> getSuppliers(Pageable pageable);
    boolean supplierWithPhoneNumberExists(PhoneNumber phoneNumber);
    boolean supplierWithRucExists(String ruc);
    boolean supplierWithSupplierNameExists(String supplierName);
    Optional<Supplier> getSupplier(UUID supplierId);
    Supplier editSupplier(UUID supplierId, EditSupplierParameters parameters);
    void deleteSupplier(UUID supplierId);
    Optional<Supplier> findByPhoneNumber(PhoneNumber phoneNumber);
    Optional<Supplier> findByRuc(String ruc);
    Optional<Supplier> findBySupplierName(String supplierName);
}
