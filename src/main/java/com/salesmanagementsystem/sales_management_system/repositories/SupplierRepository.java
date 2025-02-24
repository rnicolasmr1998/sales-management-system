package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    boolean existsByRuc(String ruc);
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
    boolean existsBySupplierName(String supplierName);
    Optional<Supplier> findByRuc(String ruc);
    Optional<Supplier> findByPhoneNumber(PhoneNumber phoneNumber);
    Optional<Supplier> findBySupplierName(String supplierName);
}
