package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
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
    @Column(name = "deuda_soles")
    private BigDecimal supplierDebtSoles;

    @NotNull
    @Column(name = "deuda_dolares")
    private BigDecimal supplierDebtDollars;

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

    public Supplier(String ruc, String supplierName, PhoneNumber phoneNumber, BigDecimal supplierDebtSoles, BigDecimal supplierDebtDollars) {
        this.ruc = ruc;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.supplierDebtSoles = supplierDebtSoles;
        this.supplierDebtDollars = supplierDebtDollars;
    }

    public void incrementDebtSoles(BigDecimal amount) {
        this.supplierDebtSoles = this.supplierDebtSoles.add(amount);
    }

    public void decrementDebtSoles(BigDecimal amount) {
        this.supplierDebtSoles = this.supplierDebtSoles.subtract(amount);
    }

    public void incrementDebtDollars(BigDecimal amount) {
        this.supplierDebtDollars = this.supplierDebtDollars.add(amount);
    }

    public void decrementDebtDollars(BigDecimal amount) {
        this.supplierDebtDollars = this.supplierDebtDollars.subtract(amount);
    }
}
