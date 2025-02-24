package com.salesmanagementsystem.sales_management_system.parameters;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.enums.Category;
import com.salesmanagementsystem.sales_management_system.enums.Currency;
import com.salesmanagementsystem.sales_management_system.enums.Measure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductParameters {
    private final String productName;
    private final String productBrand;
    private final String productDescription;
    private final BigDecimal purchasePriceUpdated;
    private final Currency currency;
    private final BigDecimal availableStock;
    private final Measure measure;
    private final Category category;
}
