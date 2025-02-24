package com.salesmanagementsystem.sales_management_system.parameters;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserParameters {
    private final FullName fullName;
    private final Gender gender;
    private final LocalDate birthday;
    private final Email email;
    private final PhoneNumber phoneNumber;
}
