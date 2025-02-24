package com.salesmanagementsystem.sales_management_system.validations;

import java.util.Optional;

import com.salesmanagementsystem.sales_management_system.editions.EditSupplierFormData;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.forms.CreateSupplierFormData;
import com.salesmanagementsystem.sales_management_system.services.SupplierService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotExistingSupplierValidator implements ConstraintValidator<NotExistingSupplier, CreateSupplierFormData> {
    private final SupplierService supplierService;

    public NotExistingSupplierValidator(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void initialize(NotExistingSupplier constraint) {
        // intentionally empty
    }

    @Override
    public boolean isValid(CreateSupplierFormData formData, ConstraintValidatorContext context) {
        if (formData == null || formData.getRuc() == null || formData.getPhoneNumber() == null || formData.getSupplierName() == null) {
            return false; // Validación temprana si el formulario, el ruc, el nombre o el número de teléfono son nulos
        }
        
        String supplierName = formData.getSupplierName();
        String ruc = formData.getRuc();
        PhoneNumber phoneNumber = new PhoneNumber(formData.getPhoneNumber());

        Optional<Supplier> existingPhoneNumber = supplierService.findByPhoneNumber(phoneNumber);
        Optional<Supplier> existingRuc = supplierService.findByRuc(ruc);
        Optional<Supplier> existingSupplierName = supplierService.findBySupplierName(supplierName);

        if (existingPhoneNumber.isPresent()) {
            // Si es una edición, verificar si el número pertenece a otro cliente
            if (formData instanceof EditSupplierFormData editFormData) {
                if (!existingPhoneNumber.get().getSupplierId().equals(editFormData.getId())) {
                    addConstraintViolation(context, "phoneNumber", "{SupplierAlreadyExistingPhoneNumber}");
                    return false;
                }
            } else {
                // Si es una creación, el númerro no debe existir
                addConstraintViolation(context, "PhoneNumber", "{SupplierAlreadyExistingPhoneNumber}");
                return false;
            }
        }

        if (existingRuc.isPresent()) {
            // Si es una edición, verificar si el número pertenece a otro cliente
            if (formData instanceof EditSupplierFormData editFormData) {
                if (!existingRuc.get().getSupplierId().equals(editFormData.getId())) {
                    addConstraintViolation(context, "ruc", "{SupplierAlreadyExistingRuc}");
                    return false;
                }
            } else {
                // Si es una creación, el númerro no debe existir
                addConstraintViolation(context, "ruc", "{SupplierAlreadyExistingRuc}");
                return false;
            }
        }

        if (existingSupplierName.isPresent()) {
            // Si es una edición, verificar si el número pertenece a otro cliente
            if (formData instanceof EditSupplierFormData editFormData) {
                if (!existingSupplierName.get().getSupplierId().equals(editFormData.getId())) {
                    addConstraintViolation(context, "supplierName", "{SupplierAlreadyExistingSupplierName}");
                    return false;
                }
            } else {
                // Si es una creación, el númerro no debe existir
                addConstraintViolation(context, "supplierName", "{SupplierAlreadyExistingSupplierName}");
                return false;
            }
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String propertyName, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(propertyName)
                .addConstraintViolation();
    }
}
