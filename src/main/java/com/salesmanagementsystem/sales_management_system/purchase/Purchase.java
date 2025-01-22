package com.salesmanagementsystem.sales_management_system.purchase;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
public class Purchase extends AbstractEntity<PurchaseId> {

    /**
     * Default constructor for JPA
     */
    protected Purchase() {
    }

    public Purchase(PurchaseId id) {
        super(id);
    }
}