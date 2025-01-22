package com.salesmanagementsystem.sales_management_system.client;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
public class Client extends AbstractEntity<ClientId> {

    /**
     * Default constructor for JPA
     */
    protected Client() {
    }

    public Client(ClientId id) {
        super(id);
    }
}