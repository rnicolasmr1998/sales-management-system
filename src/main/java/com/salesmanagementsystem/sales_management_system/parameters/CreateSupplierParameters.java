package com.salesmanagementsystem.sales_management_system.parameters;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateSupplierParameters {
    private final String ruc;
    private final String supplierName;
    private final PhoneNumber phoneNumber;
    private final BigDecimal supplierDebtSoles;
    private final BigDecimal supplierDebtDollars;

}
