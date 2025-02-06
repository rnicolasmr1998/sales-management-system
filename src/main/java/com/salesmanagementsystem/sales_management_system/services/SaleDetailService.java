package com.salesmanagementsystem.sales_management_system.services;

import com.salesmanagementsystem.sales_management_system.entities.SaleDetail;
import com.salesmanagementsystem.sales_management_system.forms.CreateSaleDetailParameters;

public interface SaleDetailService {
    SaleDetail createSaleDetail(CreateSaleDetailParameters parameters);
}
