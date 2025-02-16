package com.salesmanagementsystem.sales_management_system.editions;

import java.util.Objects;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.forms.CreateUserFormData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserFormData extends CreateUserFormData {
    private UUID id;
    private long version;

    public static EditUserFormData fromUser(User user) {
        EditUserFormData result = new EditUserFormData();
        result.setId(user.getUserId());
        result.setVersion(user.getVersion());
        result.setFirstName(user.getFullName().getFirstName());
        result.setLastName(user.getFullName().getLastName());
        result.setGender(user.getGender());
        result.setBirthday(user.getBirthday());
        result.setEmail(user.getEmail().asString());
        result.setPhoneNumber(user.getPhoneNumber().asString());
        return result;
    }

    public EditUserParameters toParameters() { // <.>
        return new EditUserParameters(version,
                new FullName(getFirstName(), getLastName()),
                getGender(),
                getBirthday(),
                new Email(getEmail()),
                new PhoneNumber(getPhoneNumber()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Son el mismo objeto
        if (o == null || getClass() != o.getClass())
            return false; // Diferente clase

        EditUserFormData that = (EditUserFormData) o;

        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                getGender() == that.getGender() &&
                Objects.equals(getBirthday(), that.getBirthday()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
public int hashCode() {
    return Objects.hash(getFirstName(), getLastName(), getGender(), getBirthday(), getEmail(), getPhoneNumber());
}
}
