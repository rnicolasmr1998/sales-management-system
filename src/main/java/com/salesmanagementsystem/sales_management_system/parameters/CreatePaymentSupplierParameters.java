package com.salesmanagementsystem.sales_management_system.parameters;

import java.math.BigDecimal;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.Currency;
import com.salesmanagementsystem.sales_management_system.embbedables.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePaymentSupplierParameters {
    private final BigDecimal amountPaid;
    private final Currency currency;
    private final PaymentMethod paymentMethod;
    private final String note;
    private final UUID supplierId;
}
