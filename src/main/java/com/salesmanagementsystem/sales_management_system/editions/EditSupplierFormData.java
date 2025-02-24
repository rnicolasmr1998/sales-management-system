package com.salesmanagementsystem.sales_management_system.editions;

import java.util.Objects;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.forms.CreateSupplierFormData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditSupplierFormData extends CreateSupplierFormData {
    private UUID id;
    private long version;

    public static EditSupplierFormData fromSupplier(Supplier supplier) {
        EditSupplierFormData result = new EditSupplierFormData();
        result.setId(supplier.getSupplierId());
        result.setVersion(supplier.getVersion());
        result.setRuc(supplier.getRuc());
        result.setSupplierName(supplier.getSupplierName());
        result.setPhoneNumber(supplier.getPhoneNumber().asString());
        result.setSupplierDebtSoles(supplier.getSupplierDebtSoles());
        result.setSupplierDebtDollars(supplier.getSupplierDebtDollars());
        return result;
    }

    public EditSupplierParameters toParameters() {
        return new EditSupplierParameters(version,
                getRuc(),
                getSupplierName(),
                new PhoneNumber(getPhoneNumber()),
                getSupplierDebtSoles(),
                getSupplierDebtDollars());
    };

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Son el mismo objeto
        if (o == null || getClass() != o.getClass())
            return false; // Diferente clase

        EditSupplierFormData that = (EditSupplierFormData) o;

        return Objects.equals(getRuc(), that.getRuc()) &&
                Objects.equals(getSupplierName(), that.getSupplierName()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getSupplierDebtSoles(), that.getSupplierDebtSoles()) &&
                Objects.equals(getSupplierDebtDollars(), that.getSupplierDebtDollars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRuc(), getSupplierName(), getPhoneNumber(), getSupplierDebtSoles(), getSupplierDebtDollars());
    }
}
