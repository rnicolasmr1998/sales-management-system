package com.salesmanagementsystem.sales_management_system.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanagementsystem.sales_management_system.components.UrlBuilder;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.forms.CreateUserFormData;
import com.salesmanagementsystem.sales_management_system.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UrlBuilder urlBuilder;

    public UserController(UserService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults({
                            @SortDefault("fullName.lastName"),
                            @SortDefault("fullName.firstName")}) Pageable pageable) {
        model.addAttribute("users", service.getUsers(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "users/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        List<Gender> genders = List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO);
        System.out.println("Genders: " + genders); // Depuración
        model.addAttribute("user", new CreateUserFormData());
        model.addAttribute("genders", genders);
        return "users/edit";
    }

    
}
