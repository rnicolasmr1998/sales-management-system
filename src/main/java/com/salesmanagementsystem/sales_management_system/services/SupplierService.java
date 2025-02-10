package com.salesmanagementsystem.sales_management_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreateSupplierParameters;

public interface SupplierService {
    Supplier createSupplier(CreateSupplierParameters parameters);
    ImmutableSet<Supplier> getAllSuppliers();
    Page<Supplier> getSuppliers(Pageable pageable);
}
