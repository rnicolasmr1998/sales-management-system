package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.PurchaseDetail;
import com.salesmanagementsystem.sales_management_system.parameters.CreatePurchaseDetailParameters;

public interface PurchaseDetailService {
    PurchaseDetail createPurchaseDetail(CreatePurchaseDetailParameters parameters);
}
