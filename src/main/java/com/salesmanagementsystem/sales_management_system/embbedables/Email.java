package com.salesmanagementsystem.sales_management_system.embbedables;

import org.springframework.util.Assert;

import com.google.common.base.MoreObjects;

import lombok.EqualsAndHashCode;
@EqualsAndHashCode
public class Email {
    private String email;

    protected Email() {
    }

    public Email(String email) {
        Assert.hasText(email, "El email no puede estar en blanco.");
        Assert.isTrue(email.contains("@"), "El email debe contener el simbolo @.");
        this.email = email;
    }

    public String asString() {
        return email;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("email", email)
                        .toString();
    }
}
