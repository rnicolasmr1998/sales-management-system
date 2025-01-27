package com.salesmanagementsystem.sales_management_system.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.salesmanagementsystem.sales_management_system.purchasedetail.PurchaseDetail;
import com.salesmanagementsystem.sales_management_system.saledetail.SaleDetail;
import com.salesmanagementsystem.sales_management_system.supplier.Supplier;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Purchase extends AbstractEntity<PurchaseId> {
    @NotNull
    @Column(name = "total_compra")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Supplier supplier;

    @OneToMany
    @JoinColumn(name = "id_detalles_compra")
    private List<PurchaseDetail> purchaseDetail;

    @NotNull
    @Column(name = "estado_compra")
    private Boolean purchaseStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    protected Purchase() {
    }

    public Purchase(PurchaseId id,
            BigDecimal totalAmount,
            Supplier supplier,
            List<PurchaseDetail> purchaseDetail,
            Boolean purchaseStatus,
            LocalDate registrationDate,
            LocalDate lastUpdateDate,
            LocalDate deleteDate) {
        super(id);
        this.totalAmount = totalAmount;
        this.supplier = supplier;
        this.purchaseDetail = purchaseDetail;
        this.purchaseStatus = purchaseStatus;
        this.registrationDate = registrationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.deleteDate = deleteDate;
    }
}