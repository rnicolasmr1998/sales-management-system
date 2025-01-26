package com.salesmanagementsystem.sales_management_system.user.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanagementsystem.sales_management_system.user.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    
    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults({
                            @SortDefault("userName.lastName"),
                            @SortDefault("userName.firstName")}) Pageable
                            pageable) {
        model.addAttribute("users", service.getUsers(pageable));
        return "users/list";
    }
    
}
