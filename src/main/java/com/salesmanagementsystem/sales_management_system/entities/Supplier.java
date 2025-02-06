package com.salesmanagementsystem.sales_management_system.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumberAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "proveedor")
@Getter
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_proveedor")
    private UUID supplierId;
    
    @NotNull
    @Column(name = "ruc_proveedor")
    private String ruc;
    
    @NotNull
    @Column(name = "nombre_proveedor")
    private String supplierName;

    @NotNull
    @Column(name = "numero_celular_proveedor")
    @Convert(converter = PhoneNumberAttributeConverter.class)
    private PhoneNumber phoneNumber;

    @NotNull
    @Column(name = "deuda")
    private Double supplierDebt;

    @NotNull
    @Column(name = "estado_proveedor")
    private Boolean supplierStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    protected Supplier() {
    }
}
