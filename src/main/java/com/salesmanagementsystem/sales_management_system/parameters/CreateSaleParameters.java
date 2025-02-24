package com.salesmanagementsystem.sales_management_system.parameters;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.entities.SaleDetail;
import com.salesmanagementsystem.sales_management_system.enums.Currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class CreateSaleParameters {
    private final UUID customerId;
    private final List<SaleDetail> saleDetails;
    private final BigDecimal totalAmount;
    private final Currency currency;
}
