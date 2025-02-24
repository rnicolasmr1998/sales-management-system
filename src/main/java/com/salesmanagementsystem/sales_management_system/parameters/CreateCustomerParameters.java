package com.salesmanagementsystem.sales_management_system.parameters;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCustomerParameters {
    private final FullName fullName;
    private final Gender gender;
    private final PhoneNumber phoneNumber;
    private final BigDecimal debt;
}
