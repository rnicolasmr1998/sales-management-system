package com.salesmanagementsystem.sales_management_system.purchase_detail;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class purchase_detailRepositoryImpl implements purchase_detailRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public purchase_detailRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public purchase_detailId nextId() {
        return new purchase_detailId(generator.getNextUniqueId());
    }
}