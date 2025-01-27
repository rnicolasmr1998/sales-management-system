package com.salesmanagementsystem.sales_management_system.sale;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class SaleRepositoryImpl implements SaleRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public SaleRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public SaleId nextId() {
        return new SaleId(generator.getNextUniqueId());
    }
}