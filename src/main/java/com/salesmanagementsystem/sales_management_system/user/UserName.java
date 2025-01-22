package com.salesmanagementsystem.sales_management_system.user;

import org.springframework.util.Assert;

import com.google.common.base.MoreObjects;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public class UserName {
    private String firstName;
    private String lastName;

    protected UserName() {
    }

    public UserName(String firstName, String lastName) {
        Assert.hasText(firstName, "El nombre del usuario no puede estar vacío");
        Assert.hasText(lastName, "El apellido del usuario no puede estar vacío");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("firstName", firstName)
                        .add("lastName", lastName)
                        .toString();
    }
}
