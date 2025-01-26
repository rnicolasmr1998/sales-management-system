package com.salesmanagementsystem.sales_management_system.embbedables;

import org.springframework.util.Assert;

import com.google.common.base.MoreObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public class FullName {
    @Column(name = "nombre")
    private String firstName;

    @Column(name = "apellido")
    private String lastName;

    protected FullName() {
    }

    public FullName(String firstName, String lastName) {
        Assert.hasText(firstName, "El nombre del usuario no puede estar vacío");
        Assert.hasText(lastName, "El apellido del usuario no puede estar vacío");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("firstName", firstName)
                        .add("lastName", lastName)
                        .toString();
    }
}
