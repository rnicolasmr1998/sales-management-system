package com.salesmanagementsystem.sales_management_system.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanagementsystem.sales_management_system.components.UrlBuilder;
import com.salesmanagementsystem.sales_management_system.services.SupplierService;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService service;
    private final UrlBuilder urlBuilder;

    public SupplierController(SupplierService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault("supplierName") Pageable pageable) {
        model.addAttribute("suppliers", service.getSuppliers(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "suppliers/list";
    }
}
