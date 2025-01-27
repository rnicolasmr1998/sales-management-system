package com.salesmanagementsystem.sales_management_system.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.customer.Customer;
import com.salesmanagementsystem.sales_management_system.embbedables.PaymentMethod;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "pago")
@Getter
public class Payment extends AbstractEntity<PaymentId> {
    @NotNull
    @Column(name = "monto_pagado")
    private BigDecimal amountPaid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago")
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(name = "notes")
    private String notas;

    @ManyToOne
    @jakarta.validation.constraints.NotNull
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    @NotNull
    @Column(name = "estado_pago")
    private Boolean paymentStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    protected Payment() {
    }

    public Payment(PaymentId id,
            BigDecimal amountPaid,
            PaymentMethod paymentMethod,
            String notas,
            Customer customer,
            Boolean paymentStatus,
            LocalDate registrationDate,
            LocalDate lastUpdateDate,
            LocalDate deleteDate) {
        super(id);
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.notas = notas;
        this.customer = customer;
        this.paymentStatus = paymentStatus;
        this.registrationDate = registrationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.deleteDate = deleteDate;
    }
}