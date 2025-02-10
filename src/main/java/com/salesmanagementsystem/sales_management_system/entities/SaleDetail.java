package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.Currency;

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
@Table(name = "detalle_venta")
@Data
@NoArgsConstructor
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle_venta")
    private UUID saleDetailId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_producto")
    private Product product;

    @NotNull
    @Column(name = "cantidad")
    private Double quantity;

    @NotNull
    @Column(name = "precio_unitario_venta")
    private BigDecimal unitePrice;

    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "moneda")
    private Currency currency;

    @NotNull
    @Column(name = "estado_detalle_venta")
    private Boolean saleDetailStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    public SaleDetail(Product product, Double quantity, BigDecimal unitePrice, BigDecimal subtotal, Currency currency) {
        this.product = product;
        this.quantity = quantity;
        this.unitePrice = unitePrice;
        this.subtotal = unitePrice.multiply(new BigDecimal(quantity));
        this.currency = currency;
    }
}
