package com.salesmanagementsystem.sales_management_system.infraestructure;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping("/")
public class RootController {
    @GetMapping
    public String root(Model model) {
        return "index";
    }
}
