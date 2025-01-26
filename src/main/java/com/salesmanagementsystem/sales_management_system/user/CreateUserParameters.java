package com.salesmanagementsystem.sales_management_system.user;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;

import lombok.Getter;

@Getter
public class CreateUserParameters {
    private final FullName fullName;
    private final Gender gender;
    private final LocalDate birthday;
    private final Email email;
    private final PhoneNumber phoneNumber;

    public CreateUserParameters(FullName fullName,
                                Gender gender,
                                LocalDate birthday,
                                Email email,
                                PhoneNumber phoneNumber) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
