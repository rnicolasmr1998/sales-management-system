package com.salesmanagementsystem.sales_management_system.user;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class User extends AbstractEntity<UserId> {
    protected User() {
    }

    public User(UserId id) {
        super(id);
    }
}