package com.salesmanagementsystem.sales_management_system.forms;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.enums.Category;
import com.salesmanagementsystem.sales_management_system.enums.Currency;
import com.salesmanagementsystem.sales_management_system.enums.Measure;
import com.salesmanagementsystem.sales_management_system.parameters.CreateProductParameters;
import com.salesmanagementsystem.sales_management_system.validations.NotExistingProduct;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupOne;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupTwo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@NotExistingProduct(groups = ValidationGroupTwo.class)
@Data
public class CreateProductFormData {
    @NotBlank
    private String productName;

    @NotBlank
    private String productBrand;

    private String productDescription;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, groups = ValidationGroupOne.class)
    private BigDecimal purchasePriceUpdated;

    @NotNull
    private Currency currency;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, groups = ValidationGroupOne.class)
    private BigDecimal availableStock;

    @NotNull
    private Measure measure;

    @NotNull
    private Category category;

    public CreateProductParameters toParameters() {
        return new CreateProductParameters(productName,
                productBrand,
                productDescription,
                purchasePriceUpdated,
                currency,
                availableStock,
                measure,
                category);
    }
}
