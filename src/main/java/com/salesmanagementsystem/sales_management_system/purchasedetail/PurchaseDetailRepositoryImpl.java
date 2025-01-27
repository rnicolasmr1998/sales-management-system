package com.salesmanagementsystem.sales_management_system.purchasedetail;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class PurchaseDetailRepositoryImpl implements PurchaseDetailRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public PurchaseDetailRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public PurchaseDetailId nextId() {
        return new PurchaseDetailId(generator.getNextUniqueId());
    }
}