package com.salesmanagementsystem.sales_management_system.supplier;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumberAttributeConverter;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedor")
@Getter
public class Supplier extends AbstractEntity<SupplierId> {
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

    public Supplier(SupplierId id,
                    String ruc,
                    String supplierName,
                    PhoneNumber phoneNumber,
                    Boolean supplierStatus,
                    LocalDate registrationDate,
                    LocalDate deleteDate,
                    Double supplierDebt,
                    LocalDate lastUpdateDate) {
        super(id);
        this.ruc = ruc;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.supplierStatus = supplierStatus;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
        this.supplierDebt = supplierDebt;
        this.lastUpdateDate = lastUpdateDate;
    }
}