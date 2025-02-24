package com.salesmanagementsystem.sales_management_system.editions;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.enums.Gender;
import com.salesmanagementsystem.sales_management_system.parameters.CreateCustomerParameters;

public class EditCustomerParameters extends CreateCustomerParameters {
    private final long version;

    public EditCustomerParameters(long version, FullName fullName, Gender gender, PhoneNumber phoneNumber, BigDecimal debt) {
        super(fullName, gender, phoneNumber, debt);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(Customer customer) {
        customer.setFullName(getFullName());
        customer.setGender(getGender());
        customer.setPhoneNumber(getPhoneNumber());
        customer.setDebt(getDebt());
    }
}
