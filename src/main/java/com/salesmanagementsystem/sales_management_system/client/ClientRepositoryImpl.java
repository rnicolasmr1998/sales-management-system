package com.salesmanagementsystem.sales_management_system.client;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public ClientRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public ClientId nextId() {
        return new ClientId(generator.getNextUniqueId());
    }
}