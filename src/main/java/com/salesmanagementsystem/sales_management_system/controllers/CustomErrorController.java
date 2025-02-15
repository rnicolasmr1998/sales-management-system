package com.salesmanagementsystem.sales_management_system.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Obtener el código de estado del error
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        System.out.println("Código de estado HTTP: " + statusCode);
        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    return "errors/404"; // Plantilla para el error 404
                case 500:
                    return "errors/500"; // Plantilla para el error 500
                default:
                    return "errors/error"; // Plantilla para otros errores
            }
        }

        return "errors/error"; // Plantilla por defecto
    }
    
    
    public String getErrorPath() {
        return "/error";
    }
}
