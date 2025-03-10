package com.salesmanagementsystem.sales_management_system.embbedables;

import org.springframework.util.Assert;

import com.google.common.base.MoreObjects;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode
@NoArgsConstructor
public class PhoneNumber {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        Assert.hasText(phoneNumber, "El número de celular no puede estar vacío.");
        this.phoneNumber = phoneNumber;
    }

    public String asString() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("phoneNumber", phoneNumber)
                        .toString();
    }
}
