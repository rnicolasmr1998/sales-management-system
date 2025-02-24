package com.salesmanagementsystem.sales_management_system.editions;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.enums.Gender;
import com.salesmanagementsystem.sales_management_system.parameters.CreateUserParameters;

public class EditUserParameters extends CreateUserParameters {
    private final long version;

    public EditUserParameters(long version, FullName fullName, Gender gender, LocalDate birthday, Email email,
            PhoneNumber phoneNumber) {
        super(fullName, gender, birthday, email, phoneNumber);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(User user) {
        user.setFullName(getFullName());
        user.setGender(getGender());
        user.setBirthday(getBirthday());
        user.setEmail(getEmail());
        user.setPhoneNumber(getPhoneNumber());
    }
}
