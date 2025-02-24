package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.enums.Currency;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_venta")
    private UUID saleId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_venta")
    private List<SaleDetail> saleDetails = new ArrayList<>();

    @NotNull
    @Column(name = "cantidad_productos")
    private Integer quantityProducts;

    @NotNull
    @Column(name = "total_venta")
    private BigDecimal totalAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "moneda")
    private Currency currency;

    @NotNull
    @Column(name = "estado_venta")
    private Boolean saleStatus = true;

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

    public Sale(Customer customer, List<SaleDetail> saleDetails, BigDecimal totalAmount,
            Currency currency) {
        this.customer = customer;
        this.saleDetails = saleDetails;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.registrationDate = LocalDateTime.now();
        this.saleStatus = true;
        this.quantityProducts = saleDetails.size();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleteDate = LocalDateTime.now();
        this.saleStatus = false;
    }
}
