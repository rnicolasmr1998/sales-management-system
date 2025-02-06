package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.Sale;
import com.salesmanagementsystem.sales_management_system.forms.CreateSaleParameters;

public interface SaleService {
    Sale createSale(CreateSaleParameters parameters);
}
