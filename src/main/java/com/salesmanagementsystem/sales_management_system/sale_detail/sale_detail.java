package com.salesmanagementsystem.sales_management_system.sale_detail;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
public class sale_detail extends AbstractEntity<sale_detailId> {

    /**
     * Default constructor for JPA
     */
    protected sale_detail() {
    }

    public sale_detail(sale_detailId id) {
        super(id);
    }
}