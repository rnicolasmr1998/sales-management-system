package com.salesmanagementsystem.sales_management_system.saledetail;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.product.Product;
import com.salesmanagementsystem.sales_management_system.sale.Sale;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "detalles_venta")
@Getter
public class SaleDetail extends AbstractEntity<SaleDetailId> {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_venta")
    private Sale sale;

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

    public SaleDetail(SaleDetailId id,
            Sale sale,
            Product product,
            Double quantity,
            BigDecimal unitePrice,
            BigDecimal subtotal,
            Boolean saleDetailStatus,
            LocalDate registrationDate,
            LocalDate lastUpdateDate,
            LocalDate deleteDate) {
        super(id);
        this.sale = sale;
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