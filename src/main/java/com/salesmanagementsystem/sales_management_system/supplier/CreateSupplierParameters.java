package com.salesmanagementsystem.sales_management_system.supplier;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;

import lombok.Getter;

@Getter
public class CreateSupplierParameters {
    private final String ruc;
    private final String supplierName;
    private final PhoneNumber phoneNumber;
    private final Boolean supplierStatus;
    private final LocalDate registrationDate;
    private final LocalDate deleteDate;
    private final Double supplierDebt;

    public CreateSupplierParameters(String ruc,
                                    String supplierName,
                                    PhoneNumber phoneNumber,
                                    Boolean supplierStatus,
                                    LocalDate registrationDate,
                                    LocalDate deleteDate,
                                    Double supplierDebt) {
        this.ruc = ruc;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.supplierStatus = supplierStatus;
        this.registrationDate = registrationDate;
        this.deleteDate = deleteDate;
        this.supplierDebt = supplierDebt;
    }
}
