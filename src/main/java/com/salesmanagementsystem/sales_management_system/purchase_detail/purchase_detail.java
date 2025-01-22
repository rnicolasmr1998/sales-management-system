package com.salesmanagementsystem.sales_management_system.purchase_detail;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
public class purchase_detail extends AbstractEntity<purchase_detailId> {

    /**
     * Default constructor for JPA
     */
    protected purchase_detail() {
    }

    public purchase_detail(purchase_detailId id) {
        super(id);
    }
}