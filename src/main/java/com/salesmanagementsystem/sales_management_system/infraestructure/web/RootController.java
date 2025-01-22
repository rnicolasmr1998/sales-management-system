package com.salesmanagementsystem.sales_management_system.infraestructure.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping
    public String root(Model model) {
        return "index";
    }
    
}
