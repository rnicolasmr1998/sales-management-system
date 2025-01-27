package com.salesmanagementsystem.sales_management_system.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.salesmanagementsystem.sales_management_system.customer.Customer;
import com.salesmanagementsystem.sales_management_system.saledetail.SaleDetail;

import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "venta")
@Getter
public class Sale extends AbstractEntity<SaleId> {
    @NotNull
    @Column(name = "total_venta")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "id_detalles_venta")
    private List<SaleDetail> saleDetails;

    @NotNull
    @Column(name = "estado_venta")
    private Boolean saleStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    protected Sale() {
    }

    public Sale(SaleId id,
                BigDecimal totalAmount,
                Customer customer,
                List<SaleDetail> saleDetails,
                Boolean saleStatus,
                LocalDate registrationDate,
                LocalDate lastUpdateDate,
                LocalDate deleteDate) {
        super(id);
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.saleDetails = saleDetails;
        this.saleStatus = saleStatus;
        this.registrationDate = registrationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.deleteDate = deleteDate;
    }
}