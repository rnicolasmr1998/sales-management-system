package com.salesmanagementsystem.sales_management_system.editions;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreateSupplierParameters;

public class EditSupplierParameters extends CreateSupplierParameters {
    private final long version;

    public EditSupplierParameters(long version, String ruc, String supplierName, PhoneNumber phoneNumber,
            BigDecimal supplierDebtSoles, BigDecimal supplierDebtDollars) {
        super(ruc, supplierName, phoneNumber, supplierDebtSoles, supplierDebtDollars);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(Supplier supplier) {
        supplier.setRuc(getRuc());
        supplier.setSupplierName(getSupplierName());
        supplier.setPhoneNumber(getPhoneNumber());
        supplier.setSupplierDebtSoles(getSupplierDebtSoles());
        supplier.setSupplierDebtDollars(getSupplierDebtDollars());
    }
}
