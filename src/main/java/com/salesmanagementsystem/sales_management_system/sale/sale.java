package com.salesmanagementsystem.sales_management_system.sale;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
public class sale extends AbstractEntity<saleId> {

    /**
     * Default constructor for JPA
     */
    protected sale() {
    }

    public sale(saleId id) {
        super(id);
    }
}