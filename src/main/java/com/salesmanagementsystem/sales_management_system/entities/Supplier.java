package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumberAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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
    private Boolean supplierStatus = true;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDateTime registrationDate;

    @Version
    @Column(name = "version")
    private long version;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime deleteDate;

    public Supplier(String ruc, String supplierName, PhoneNumber phoneNumber, BigDecimal supplierDebtSoles,
            BigDecimal supplierDebtDollars) {
        this.ruc = ruc;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.supplierDebtSoles = supplierDebtSoles;
        this.supplierDebtDollars = supplierDebtDollars;
        this.registrationDate = LocalDateTime.now();
        this.supplierStatus = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleteDate = LocalDateTime.now();
        this.supplierStatus = false;
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
