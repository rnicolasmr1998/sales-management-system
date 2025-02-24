package com.salesmanagementsystem.sales_management_system.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.salesmanagementsystem.sales_management_system.editions.EditProductParameters;
import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
import com.salesmanagementsystem.sales_management_system.parameters.CreateProductParameters;
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

    @Override
    @Transactional(readOnly = true)
    public boolean productWithProductNameExists(String productName) {
        return repository.existsByProductName(productName);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean productWithProductBrandExists(String productBrand) {
        return repository.existsByProductName(productBrand);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProduct(UUID productId) {
        return repository.findById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findByProductBrandAndProductName(String productBrand, String productName) {
        return repository.findByProductBrandAndProductName(productBrand, productName);
    }

    @Override
    @Transactional
    public Product editProduct(UUID productId, EditProductParameters parameters) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException(productId, "Producto"));
        if (parameters.getVersion() != product.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(Supplier.class, productId.toString());
        }
        product.updatePurchasePrice(parameters.getPurchasePriceUpdated());
        parameters.update(product);
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        repository.deleteById(productId);
    }
}
