package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.forms.CreateSupplierParameters;

public interface SupplierService {
    Supplier createSupplier(CreateSupplierParameters parameters);
}
