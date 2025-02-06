package com.salesmanagementsystem.sales_management_system.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.EmailAttributeConverter;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumberAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private UUID userId;

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

    @NotNull
    @Column(name = "estado_usuario")
    private Boolean userStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    public User(FullName fullName, Gender gender, LocalDate birthday, Email email, PhoneNumber phoneNumber) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
