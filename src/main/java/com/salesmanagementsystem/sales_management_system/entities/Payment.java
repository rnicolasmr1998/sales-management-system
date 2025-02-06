package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.PaymentMethod;

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
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "pago")
@Getter
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pago")
    private UUID paymentId;

    @NotNull
    @Column(name = "monto_pagado")
    private BigDecimal amountPaid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago")
    private PaymentMethod paymentMethod;

    @Column(name = "notes")
    private String notas;

    @ManyToOne
    @NotNull
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
}
