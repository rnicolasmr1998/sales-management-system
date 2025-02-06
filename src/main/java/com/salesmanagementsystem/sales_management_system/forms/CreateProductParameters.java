package com.salesmanagementsystem.sales_management_system.forms;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.Category;
import com.salesmanagementsystem.sales_management_system.embbedables.Currency;
import com.salesmanagementsystem.sales_management_system.embbedables.Measure;

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
    private final Double availableStock;
    private final Measure measure;
    private final Category category;
}
