package com.salesmanagementsystem.sales_management_system.forms;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;

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
