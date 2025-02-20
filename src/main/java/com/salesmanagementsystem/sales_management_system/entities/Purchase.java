package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.embbedables.Currency;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compra")
@Data
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_compra")
    private UUID purchaseId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proveedor")
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    @JoinColumn(name = "id_compra")
    private List<PurchaseDetail> purchaseDetails;

    @NotNull
    @Column(name = "total_compra")
    private BigDecimal totalAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "moneda")
    private Currency currency;

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
}
