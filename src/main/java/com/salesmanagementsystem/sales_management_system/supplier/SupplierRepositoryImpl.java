package com.salesmanagementsystem.sales_management_system.supplier;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class SupplierRepositoryImpl implements SupplierRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public SupplierRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public SupplierId nextId() {
        return new SupplierId(generator.getNextUniqueId());
    }
}