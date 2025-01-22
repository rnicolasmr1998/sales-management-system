package com.salesmanagementsystem.sales_management_system.sale_detail;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class sale_detailRepositoryImpl implements sale_detailRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public sale_detailRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public sale_detailId nextId() {
        return new sale_detailId(generator.getNextUniqueId());
    }
}