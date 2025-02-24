package com.salesmanagementsystem.sales_management_system.forms;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.parameters.CreateSupplierParameters;
import com.salesmanagementsystem.sales_management_system.validations.NotExistingSupplier;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupOne;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupTwo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@NotExistingSupplier(groups = ValidationGroupTwo.class)
@Data
public class CreateSupplierFormData {
    @NotBlank
    @Pattern(regexp = "^[12]\\d{10}$", groups = ValidationGroupOne.class)
    private String ruc;

    @NotBlank
    private String supplierName;

    @NotBlank
    @Pattern(regexp = "^9\\d{8}$", groups = ValidationGroupOne.class)
    private String phoneNumber;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, groups = ValidationGroupOne.class)
    private BigDecimal supplierDebtSoles;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, groups = ValidationGroupOne.class)
    private BigDecimal supplierDebtDollars;

    public CreateSupplierParameters toParameters() {
        return new CreateSupplierParameters(ruc,
                supplierName,
                new PhoneNumber(phoneNumber),
                supplierDebtSoles,
                supplierDebtDollars);
    }
}
