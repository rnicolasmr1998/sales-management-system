package com.salesmanagementsystem.sales_management_system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.forms.CreateProductParameters;

public interface ProductService {
    Product createProduct(CreateProductParameters parameters);
    ImmutableSet<Product> getAllProducts();
    Page<Product> getProducts(Pageable pageable);
}
