package com.salesmanagementsystem.sales_management_system.purchasedetail;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.product.Product;
import com.salesmanagementsystem.sales_management_system.purchase.Purchase;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class PurchaseDetail extends AbstractEntity<PurchaseDetailId> {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_compra")
    private Purchase purchase;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_producto")
    private Product product;

    @NotNull
    @Column(name = "cantidad")
    private Double quantity;

    @NotNull
    @Column(name = "precio_unitario_compra")
    private BigDecimal unitePrice;

    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @NotNull
    @Column(name = "estado_detalle_compra")
    private Boolean saleDetailStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    protected PurchaseDetail() {
    }

    public PurchaseDetail(PurchaseDetailId id,
            Purchase purchase,
            Product product,
            Double quantity,
            BigDecimal unitePrice,
            BigDecimal subtotal,
            Boolean saleDetailStatus,
            LocalDate registrationDate,
            LocalDate lastUpdateDate,
            LocalDate deleteDate) {
        super(id);
        this.purchase = purchase;
        this.product = product;
        this.quantity = quantity;
        this.unitePrice = unitePrice;
        this.subtotal = subtotal;
        this.saleDetailStatus = saleDetailStatus;
        this.registrationDate = registrationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.deleteDate = deleteDate;
    }
}