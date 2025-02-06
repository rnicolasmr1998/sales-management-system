package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.Payment;
import com.salesmanagementsystem.sales_management_system.forms.CreatePaymentParameters;

public interface PaymentService {
    Payment createPayment(CreatePaymentParameters parameters);
}
