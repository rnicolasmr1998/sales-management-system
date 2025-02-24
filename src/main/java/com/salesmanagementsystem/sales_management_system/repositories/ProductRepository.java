package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.sales_management_system.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByProductName(String productName);
    boolean existsByProductBrand(String productBrand);
    Optional<Product> findByProductBrandAndProductName(String productBrand, String productName);
}
