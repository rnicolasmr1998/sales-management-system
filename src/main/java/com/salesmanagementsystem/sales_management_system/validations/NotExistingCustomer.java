package com.salesmanagementsystem.sales_management_system.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE) //<.>
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistingCustomerValidator.class)
public @interface NotExistingCustomer {
    String message() default "{CustomerAlreadyExisting}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
