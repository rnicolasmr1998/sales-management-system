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
    private final Boolean userStatus;
    private final LocalDate registrationDate;
    private final LocalDate deleteDate;


    public CreateUserParameters(FullName fullName,
                                Gender gender,
                                LocalDate birthday,
                                Email email,
                                PhoneNumber phoneNumber,
                                Boolean userStatus,
                                LocalDate registrationDate,
                                LocalDate deleteDate) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userStatus = userStatus;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
    }
}
