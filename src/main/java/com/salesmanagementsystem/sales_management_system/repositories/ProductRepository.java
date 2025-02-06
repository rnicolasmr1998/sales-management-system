package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.sales_management_system.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
