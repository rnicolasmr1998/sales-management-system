package com.salesmanagementsystem.sales_management_system.forms;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.salesmanagementsystem.sales_management_system.embbedables.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserFormData {
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private Gender  gender;
    
    @NotBlank
    @Email
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotBlank
    @Pattern(regexp = "^9\\d{8}$")
    private String phoneNumber;
}
