package com.salesmanagementsystem.sales_management_system.customer;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumberAttributeConverter;
import com.salesmanagementsystem.sales_management_system.sale.Sale;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "cliente")
@Getter
public class Customer extends AbstractEntity<CustomerId> {
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

    protected Customer() {
    }

    public Customer(CustomerId id,
                    FullName fullName,
                    Gender gender,
                    PhoneNumber phoneNumber,
                    Boolean customerStatus,
                    LocalDate registrationDate,
                    LocalDate deleteDate) {
        super(id);
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.customerStatus = customerStatus;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
    }
}