package com.salesmanagementsystem.sales_management_system.purchase;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class PurchaseRepositoryImpl implements PurchaseRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public PurchaseRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public PurchaseId nextId() {
        return new PurchaseId(generator.getNextUniqueId());
    }
}