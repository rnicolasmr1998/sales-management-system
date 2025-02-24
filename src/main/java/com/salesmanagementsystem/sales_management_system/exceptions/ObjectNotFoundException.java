package com.salesmanagementsystem.sales_management_system.exceptions;

import java.util.UUID;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(UUID id, String objectName) {
        super(String.format("%s con id %s no encontrado", objectName, id.toString()));
    }
}
