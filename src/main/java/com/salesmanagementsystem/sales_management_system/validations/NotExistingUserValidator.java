package com.salesmanagementsystem.sales_management_system.validations;

import java.util.Optional;

import com.salesmanagementsystem.sales_management_system.editions.EditUserFormData;
import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.forms.CreateUserFormData;
import com.salesmanagementsystem.sales_management_system.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, CreateUserFormData> {
    private final UserService userService;

    public NotExistingUserValidator(UserService userService) { // <.>
        this.userService = userService;
    }

    public void initialize(NotExistingUser constraint) {
        // intentionally empty
    }

    // tag::isValid[]
    public boolean isValid(CreateUserFormData formData, ConstraintValidatorContext context) {
        if (formData == null || formData.getEmail() == null) {
            return false; // Validación temprana si el formulario o el email son nulos
        }
    
        Email email = new Email(formData.getEmail());
    
        // Verificar si el correo ya existe
        Optional<User> existingUser = userService.findByEmail(email);
    
        if (existingUser.isPresent()) {
            // Si es una edición, verificar si el correo pertenece a otro usuario
            if (formData instanceof EditUserFormData editFormData) {
                if (!existingUser.get().getUserId().equals(editFormData.getId())) {
                    addConstraintViolation(context, "email", "{UserAlreadyExisting}");
                    return false;
                }
            } else {
                // Si es una creación, el correo no debe existir
                addConstraintViolation(context, "email", "{UserAlreadyExisting}");
                return false;
            }
        }
    
        return true; // El formulario es válido
    }
    // end::isValid[]

    private void addConstraintViolation(ConstraintValidatorContext context, String propertyName, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(propertyName)
                .addConstraintViolation();
    }
}
