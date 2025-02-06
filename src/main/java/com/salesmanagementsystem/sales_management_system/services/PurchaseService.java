package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.Purchase;
import com.salesmanagementsystem.sales_management_system.forms.CreatePurchaseParameters;

public interface PurchaseService {
    Purchase createPurchase(CreatePurchaseParameters parameters);
}
