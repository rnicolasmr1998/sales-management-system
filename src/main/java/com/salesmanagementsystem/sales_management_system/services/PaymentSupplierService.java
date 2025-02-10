package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.PaymentSupplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreatePaymentSupplierParameters;

public interface PaymentSupplierService {
    PaymentSupplier createPaymentSupplier(CreatePaymentSupplierParameters parameters);
}
