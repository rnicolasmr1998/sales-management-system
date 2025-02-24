package com.salesmanagementsystem.sales_management_system.editions;

import java.math.BigDecimal;

import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.enums.Category;
import com.salesmanagementsystem.sales_management_system.enums.Currency;
import com.salesmanagementsystem.sales_management_system.enums.Measure;
import com.salesmanagementsystem.sales_management_system.parameters.CreateProductParameters;

public class EditProductParameters extends CreateProductParameters {
    private final long version;

    public EditProductParameters(long version, String productName, String productBrand, String productDescription,
            BigDecimal purchasePriceUpdated, Currency currency, BigDecimal availableStock, Measure measure,
            Category category) {
        super(productName, productBrand, productDescription, purchasePriceUpdated, currency, availableStock, measure,
                category);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(Product product) {
        product.setProductName(getProductName());
        product.setProductBrand(getProductBrand());
        product.setProductDescription(getProductDescription());
        product.setPurchasePriceUpdated(getPurchasePriceUpdated());
        product.setCurrency(getCurrency());
        product.setAvailableStock(getAvailableStock());
        product.setMeasure(getMeasure());
        product.setCategory(getCategory());
    }
}
