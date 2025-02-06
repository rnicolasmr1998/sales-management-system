package com.salesmanagementsystem.sales_management_system.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.forms.CreateProductParameters;
import com.salesmanagementsystem.sales_management_system.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Product createProduct(CreateProductParameters parameters) {
        Product product = new Product(parameters.getProductName(),
                parameters.getProductBrand(),
                parameters.getProductDescription(),
                parameters.getPurchasePriceUpdated(),
                parameters.getCurrency(),
                parameters.getAvailableStock(),
                parameters.getMeasure(),
                parameters.getCategory());
        product.setPurchasePriceOld(parameters.getPurchasePriceUpdated());
        product.setProductStatus(true);
        product.setRegistrationDate(LocalDate.now());
        product.setLastUpdateDate(LocalDate.now());
        return repository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ImmutableSet<Product> getAllProducts() {
        return ImmutableSet.copyOf(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
