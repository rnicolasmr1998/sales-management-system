package com.salesmanagementsystem.sales_management_system.validations;

import java.util.Optional;

import com.salesmanagementsystem.sales_management_system.editions.EditCustomerFormData;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.forms.CreateCustomerFormData;
import com.salesmanagementsystem.sales_management_system.services.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotExistingCustomerValidator implements ConstraintValidator<NotExistingCustomer, CreateCustomerFormData> {
    private final CustomerService customerService;

    public NotExistingCustomerValidator(CustomerService customerService) { // <.>
        this.customerService = customerService;
    }

    public void initialize(NotExistingCustomer constraint) {
        // intentionally empty
    }

    // tag::isValid[]
    @Override
    public boolean isValid(CreateCustomerFormData formData, ConstraintValidatorContext context) {
        if (formData == null || formData.getPhoneNumber() == null) {
            return false; // Validación temprana si el formulario o el phoneNumber son nulos
        }

        PhoneNumber phoneNumber = new PhoneNumber(formData.getPhoneNumber());

        // Verificar si el phoneNumber ya existe
        Optional<Customer> existingCustomer = customerService.findByPhoneNumber(phoneNumber);
        
        if (existingCustomer.isPresent()) {
            // Si es una edición, verificar si el número pertenece a otro cliente
            if (formData instanceof EditCustomerFormData editFormData) {
                if (!existingCustomer.get().getCustomerId().equals(editFormData.getId())) {
                    addConstraintViolation(context, "phoneNumber", "{CustomerAlreadyExisting}");
                    return false;
                }
            } else {
                // Si es una creación, el númerro no debe existir
                addConstraintViolation(context, "phoneNumber", "{CustomerAlreadyExisting}");
                return false;
            }
        }

        return true;
    }
    // end::isValid[]

    private void addConstraintViolation(ConstraintValidatorContext context, String propertyName, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(propertyName)
                .addConstraintViolation();
    }
}
