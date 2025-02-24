package com.salesmanagementsystem.sales_management_system.editions;

import java.util.Objects;
import java.util.UUID;

import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.forms.CreateProductFormData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProductFormData extends CreateProductFormData {
    private UUID id;
    private long version;

    public static EditProductFormData fromProduct(Product product) {
        EditProductFormData editProductFormData = new EditProductFormData();
        editProductFormData.setId(product.getProductId());
        editProductFormData.setProductName(product.getProductName());
        editProductFormData.setProductBrand(product.getProductBrand());
        editProductFormData.setProductDescription(product.getProductDescription());
        editProductFormData.setPurchasePriceUpdated(product.getPurchasePriceUpdated());
        editProductFormData.setCurrency(product.getCurrency());
        editProductFormData.setAvailableStock(product.getAvailableStock());
        editProductFormData.setMeasure(product.getMeasure());
        editProductFormData.setCategory(product.getCategory());
        editProductFormData.setVersion(product.getVersion());
        return editProductFormData;
    }

    public EditProductParameters toParameters() {
        return new EditProductParameters(version,
                getProductName(),
                getProductBrand(),
                getProductDescription(),
                getPurchasePriceUpdated(),
                getCurrency(),
                getAvailableStock(),
                getMeasure(),
                getCategory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Son el mismo objeto
        if (o == null || getClass() != o.getClass())
            return false; // Diferente clase

        EditProductFormData that = (EditProductFormData) o;

        return Objects.equals(getProductName(), that.getProductName()) &&
                Objects.equals(getProductBrand(), that.getProductBrand()) &&
                Objects.equals(getProductDescription(), that.getProductDescription()) &&
                Objects.equals(getPurchasePriceUpdated(), that.getPurchasePriceUpdated()) &&
                getCurrency() == that.getCurrency() &&
                Objects.equals(getAvailableStock(), that.getAvailableStock()) &&
                getMeasure() == that.getMeasure() &&
                getCategory() == that.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getProductBrand(), getProductDescription(), getPurchasePriceUpdated(),
                getCurrency(), getAvailableStock(), getMeasure(), getCategory());
    }
}
