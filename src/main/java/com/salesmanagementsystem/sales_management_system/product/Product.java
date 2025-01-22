package com.salesmanagementsystem.sales_management_system.product;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
public class Product extends AbstractEntity<ProductId> {

    /**
     * Default constructor for JPA
     */
    protected Product() {
    }

    public Product(ProductId id) {
        super(id);
    }
}