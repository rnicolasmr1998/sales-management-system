package com.salesmanagementsystem.sales_management_system.sale;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class saleRepositoryImpl implements saleRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public saleRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public saleId nextId() {
        return new saleId(generator.getNextUniqueId());
    }
}