package com.salesmanagementsystem.sales_management_system.validations;

import java.util.Optional;

import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.forms.CreateProductFormData;
import com.salesmanagementsystem.sales_management_system.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotExistingProductValidator implements ConstraintValidator<NotExistingProduct, CreateProductFormData> {
    private final ProductService productService;

    public NotExistingProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public void initialize(NotExistingProduct constraint) {
        // intentionally empty
    }

    @Override
    public boolean isValid(CreateProductFormData formData, ConstraintValidatorContext context) {
        if (formData == null || formData.getProductName() == null || formData.getProductBrand() == null) {
            return false;
        }

        String productName = formData.getProductName().trim().toUpperCase();
        String productBrand = formData.getProductBrand().trim().toUpperCase();

        Optional<Product> existingProduct = productService.findByProductBrandAndProductName(productName, productBrand);

        if (existingProduct.isPresent()) {
            addConstraintViolation(context, "productName", "{ProductAlreadyExisting}");
            addConstraintViolation(context, "productBrand", "{ProductAlreadyExisting}");
            return false;
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
