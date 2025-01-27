package com.salesmanagementsystem.sales_management_system.product;

import java.time.LocalDate;

import com.salesmanagementsystem.sales_management_system.embbedables.Category;
import com.salesmanagementsystem.sales_management_system.embbedables.Measure;
import com.salesmanagementsystem.sales_management_system.supplier.Supplier;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.github.wimdeblauwe.jpearl.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "producto")
@Getter
public class Product extends AbstractEntity<ProductId> {
    @NotNull
    @Column(name = "nombre_producto")
    private String productName;

    @Column(name = "descripcion_producto")
    private String productDescription;

    @NotNull
    @Column(name = "precio_compra")
    private Double purchasePrice;

    @NotNull
    @Column(name="cantidad_disponible")
    private Double availableStock;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Supplier supplier;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida")
    private Measure measure;

    @NotNull
    @Column(name = "marca_producto")
    private String productBrand;

    @NotNull
    @Column(name = "estado_producto")
    private Boolean productStatus;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate registrationDate;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDate lastUpdateDate;

    @Column(name = "fecha_eliminacion")
    private LocalDate deleteDate;

    
    protected Product() {
    }

    public Product(ProductId id,
                    String productName,
                    String productDescription,
                    Double purchasePrice,
                    Double availableStock,
                    LocalDate registrationDate,
                    Category category,
                    Supplier supplier,
                    Measure measure,
                    String productBrand,
                    Boolean productStatus,
                    LocalDate lastUpdateDate,
                    LocalDate deleteDate) {
        super(id);
        this.productName = productName;
        this.productDescription = productDescription;
        this.purchasePrice = purchasePrice;
        this.availableStock = availableStock;
        this.registrationDate = registrationDate;
        this.category = category;
        this.supplier = supplier;
        this.measure = measure;
        this.productBrand = productBrand;
        this.productStatus = productStatus;
        this.lastUpdateDate = lastUpdateDate;
        this.deleteDate = deleteDate;
    }
}