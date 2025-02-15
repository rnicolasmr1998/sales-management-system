package com.salesmanagementsystem.sales_management_system.forms;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.parameters.CreateUserParameters;
import com.salesmanagementsystem.sales_management_system.validations.NotExistingUser;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupOne;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupTwo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@NotExistingUser(groups = ValidationGroupTwo.class)
@Data
public class CreateUserFormData {
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotNull
    private Gender  gender;
    
    @NotBlank
    @Email(groups = ValidationGroupOne.class)
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotBlank
    @Pattern(regexp = "^9\\d{8}$", groups = ValidationGroupTwo.class)
    private String phoneNumber;

    public CreateUserParameters toParameters() {
        return new CreateUserParameters(new FullName(firstName, lastName),
                                        gender,
                                        birthday,
                                        new com.salesmanagementsystem.sales_management_system.embbedables.Email(email),
                                        new PhoneNumber(phoneNumber));
    }
}
