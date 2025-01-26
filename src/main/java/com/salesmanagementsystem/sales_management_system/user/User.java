package com.salesmanagementsystem.sales_management_system.user;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.EmailAttributeConverter;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumberAttributeConverter;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "usuario")
@Getter
public class User extends AbstractEntity<UserId> {
    @NotNull
    private FullName fullName;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Gender gender;
    
    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDate birthday;

    @NotNull
    @Column(name = "correo_electronico")
    @Convert(converter = EmailAttributeConverter.class)
    private Email email;

    @NotNull
    @Column(name = "numero_celular")
    @Convert(converter = PhoneNumberAttributeConverter.class)
    private PhoneNumber phoneNumber;

    protected User() {
    }

    public User(UserId id,
                FullName fullName,
                Gender gender,
                LocalDate birthday,
                Email email,
                PhoneNumber phoneNumber) {
        super(id);
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}