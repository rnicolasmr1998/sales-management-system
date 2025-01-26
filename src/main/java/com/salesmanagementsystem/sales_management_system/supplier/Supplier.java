package com.salesmanagementsystem.sales_management_system.supplier;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Entity;

@Entity
public class Supplier extends AbstractEntity<SupplierId> {
    /**
     * Default constructor for JPA
     */
    protected Supplier() {
    }

    public Supplier(SupplierId id) {
        super(id);
    }
}