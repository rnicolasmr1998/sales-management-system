package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "detalle_venta")
@Getter
@AllArgsConstructor
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

    protected SaleDetail() {
    }
}
