package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
@Table(name = "cliente")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private UUID customerId;

    @NotNull
    private FullName fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Gender gender;

    @NotNull
    @Column(name = "numero_celular")
    @Convert(converter = PhoneNumberAttributeConverter.class)
    private PhoneNumber phoneNumber;

    @NotNull
    @Column(name = "deuda_cliente")
    private BigDecimal debt;

    @NotNull
    @Column(name = "estado_cliente")
    private Boolean customerStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    public Customer(FullName fullName, Gender gender, PhoneNumber phoneNumber, BigDecimal debt) {
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.debt = debt;
    }
}
