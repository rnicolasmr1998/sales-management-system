package com.salesmanagementsystem.sales_management_system.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanagementsystem.sales_management_system.components.UrlBuilder;
import com.salesmanagementsystem.sales_management_system.services.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;
    private final UrlBuilder urlBuilder;

    public CustomerController(CustomerService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults({
                            @SortDefault("fullName.lastName"),
                            @SortDefault("fullName.firstName")}) Pageable pageable) {
        model.addAttribute("customers", service.getCustomers(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "customers/list";
    }
}
