package com.salesmanagementsystem.sales_management_system.editions;

import java.util.Objects;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.forms.CreateCustomerFormData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCustomerFormData extends CreateCustomerFormData {
    private UUID id;
    private long version;

    public static EditCustomerFormData fromCustomer(Customer customer) {
        EditCustomerFormData result = new EditCustomerFormData();
        result.setId(customer.getCustomerId());
        result.setVersion(customer.getVersion());
        result.setFirstName(customer.getFullName().getFirstName());
        result.setLastName(customer.getFullName().getLastName());
        result.setGender(customer.getGender());
        result.setPhoneNumber(customer.getPhoneNumber().asString());
        result.setDebt(customer.getDebt());
        return result;
    }

    public EditCustomerParameters toParameters() {
        return new EditCustomerParameters(version,
                new FullName(getFirstName(), getLastName()),
                getGender(),
                new PhoneNumber(getPhoneNumber()),
                getDebt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Son el mismo objeto
        if (o == null || getClass() != o.getClass())
            return false; // Diferente clase

        EditCustomerFormData that = (EditCustomerFormData) o;

        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                getGender() == that.getGender() &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getDebt(), that.getDebt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getGender(), getPhoneNumber(), getDebt());
    }
}
