package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.enums.Currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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
    private BigDecimal quantity;

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
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Version
    @Column(name = "version")
    private long version;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDateTime lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime deleteDate;

    public SaleDetail(Product product, BigDecimal quantity, BigDecimal unitePrice, BigDecimal subtotal, Currency currency) {
        this.product = product;
        this.quantity = quantity;
        this.unitePrice = unitePrice;
        this.subtotal = unitePrice.multiply(quantity);
        this.currency = currency;
        this.registrationDate = LocalDateTime.now();
        this.saleDetailStatus = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleteDate = LocalDateTime.now();
        this.saleDetailStatus = false;
    }
}
