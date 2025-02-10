package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.sales_management_system.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID>{

}
