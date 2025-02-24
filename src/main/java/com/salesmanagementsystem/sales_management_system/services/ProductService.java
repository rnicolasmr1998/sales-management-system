package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditProductParameters;
import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.parameters.CreateProductParameters;

public interface ProductService {
    Product createProduct(CreateProductParameters parameters);
    ImmutableSet<Product> getAllProducts();
    Page<Product> getProducts(Pageable pageable);
    boolean productWithProductNameExists(String productName);
    boolean productWithProductBrandExists(String productBrand);
    Optional<Product> getProduct(UUID productId);
    Product editProduct(UUID productId, EditProductParameters parameters);
    void deleteProduct(UUID productId);
    Optional<Product> findByProductBrandAndProductName(String productBrand, String productName);
}
