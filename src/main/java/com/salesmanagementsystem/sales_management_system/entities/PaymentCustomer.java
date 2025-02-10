package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.Currency;
import com.salesmanagementsystem.sales_management_system.embbedables.PaymentMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pago_cliente")
@Data
@NoArgsConstructor
public class PaymentCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pago_client")
    private UUID paymentCustomerId;

    @NotNull
    @Column(name = "monto_pagado")
    private BigDecimal amountPaid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "moneda")
    private Currency currency;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago")
    private PaymentMethod paymentMethod;

    @Column(name = "nota")
    private String note;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    @NotNull
    @Column(name = "estado_pago")
    private Boolean paymentCustomerStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    public PaymentCustomer(BigDecimal amountPaid, Currency currency, PaymentMethod paymentMethod, String note, Customer customer) {
        this.amountPaid = amountPaid;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.note = note;
        this.customer = customer;
    }
}
