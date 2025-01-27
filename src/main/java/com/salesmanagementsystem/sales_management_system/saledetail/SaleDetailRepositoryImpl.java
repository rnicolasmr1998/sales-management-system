package com.salesmanagementsystem.sales_management_system.saledetail;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class SaleDetailRepositoryImpl implements SaleDetailRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public SaleDetailRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public SaleDetailId nextId() {
        return new SaleDetailId(generator.getNextUniqueId());
    }
}