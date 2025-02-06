package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "venta")
@Getter
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_venta")
    private UUID saleId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Customer customer;

    
    @NotNull
    @OneToMany
    @JoinColumn(name = "id_detalle_venta")
    private List<SaleDetail> saleDetails;

    @NotNull
    @Column(name = "total_venta")
    private BigDecimal totalAmount;

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
}
