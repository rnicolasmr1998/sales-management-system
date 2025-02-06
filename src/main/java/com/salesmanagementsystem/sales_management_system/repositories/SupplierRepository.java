package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanagementsystem.sales_management_system.entities.Supplier;

@Transactional(readOnly = true)
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

}
