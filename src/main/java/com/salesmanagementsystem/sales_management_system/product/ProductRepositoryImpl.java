package com.salesmanagementsystem.sales_management_system.product;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public ProductRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public ProductId nextId() {
        return new ProductId(generator.getNextUniqueId());
    }
}