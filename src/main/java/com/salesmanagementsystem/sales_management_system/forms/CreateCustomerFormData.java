package com.salesmanagementsystem.sales_management_system.forms;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.enums.Gender;
import com.salesmanagementsystem.sales_management_system.parameters.CreateCustomerParameters;
import com.salesmanagementsystem.sales_management_system.validations.NotExistingCustomer;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupOne;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupTwo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@NotExistingCustomer(groups = ValidationGroupTwo.class)
@Data
public class CreateCustomerFormData {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    @NotBlank
    @Pattern(regexp = "^9\\d{8}$", groups = ValidationGroupOne.class)
    private String phoneNumber;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, groups = ValidationGroupOne.class)
    private BigDecimal debt;

    public CreateCustomerParameters toParameters() {
        return new CreateCustomerParameters(new FullName(firstName, lastName),
                gender,
                new PhoneNumber(phoneNumber),
                debt);
    }
}
