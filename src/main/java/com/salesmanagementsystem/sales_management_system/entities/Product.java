package com.salesmanagementsystem.sales_management_system.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.enums.Category;
import com.salesmanagementsystem.sales_management_system.enums.Currency;
import com.salesmanagementsystem.sales_management_system.enums.Measure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_producto")
    private UUID productId;

    @NotNull
    @Column(name = "nombre_producto")
    private String productName;

    @NotNull
    @Column(name = "marca_producto")
    private String productBrand;

    @Column(name = "descripcion_producto")
    private String productDescription;

    @NotNull
    @Column(name = "precio_compra_antiguo")
    private BigDecimal purchasePriceOld;

    @NotNull
    @Column(name = "precio_compra_nuevo")
    private BigDecimal purchasePriceUpdated;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "moneda")
    private Currency currency;

    @NotNull
    @Column(name = "cantidad_disponible")
    private BigDecimal availableStock;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida")
    private Measure measure;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Category category;

    @NotNull
    @Column(name = "estado_producto")
    private Boolean productStatus = true;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Version
    @Column(name = "version")
    private long version;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime deleteDate;

    public Product(String productName, String productBrand, String productDescription, BigDecimal purchasePriceUpdated,
            Currency currency, BigDecimal availableStock, Measure measure, Category category) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.purchasePriceOld = purchasePriceUpdated;
        this.purchasePriceUpdated = purchasePriceUpdated;
        this.currency = currency;
        this.availableStock = availableStock;
        this.measure = measure;
        this.category = category;
        this.registrationDate = LocalDateTime.now();
        this.productStatus = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleteDate = LocalDateTime.now();
        this.productStatus = false;
    }

    public void incrementAvailableStock(BigDecimal amount) {
        this.availableStock = this.availableStock.add(amount);
    }

    public void decrementAvailableStock(BigDecimal amount) {
        this.availableStock = this.availableStock.subtract(amount);
    }

    public void updatePurchasePrice(BigDecimal purchasePriceUpdated) {
        this.purchasePriceOld = this.purchasePriceUpdated;
        this.purchasePriceUpdated = purchasePriceUpdated;
    }
}
