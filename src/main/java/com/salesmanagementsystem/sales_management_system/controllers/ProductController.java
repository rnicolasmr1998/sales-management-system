package com.salesmanagementsystem.sales_management_system.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesmanagementsystem.sales_management_system.components.UrlBuilder;
import com.salesmanagementsystem.sales_management_system.editions.EditProductFormData;
import com.salesmanagementsystem.sales_management_system.entities.Product;
import com.salesmanagementsystem.sales_management_system.enums.Category;
import com.salesmanagementsystem.sales_management_system.enums.Currency;
import com.salesmanagementsystem.sales_management_system.enums.EditMode;
import com.salesmanagementsystem.sales_management_system.enums.Measure;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
import com.salesmanagementsystem.sales_management_system.forms.CreateProductFormData;
import com.salesmanagementsystem.sales_management_system.services.ProductService;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupSequence;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final UrlBuilder urlBuilder;

    public ProductController(ProductService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
            @SortDefault.SortDefaults({
                    @SortDefault("productName"),
                    @SortDefault("productBrand") }) Pageable pageable) {
        model.addAttribute("products", service.getProducts(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "products/list";
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new CreateProductFormData());
        model.addAttribute("currencies", List.of(Currency.values()));
        model.addAttribute("measures", List.of(Measure.values()));
        model.addAttribute("categories", List.of(Category.values()));
        model.addAttribute("editMode", EditMode.CREAR);
        return "products/edit";
    }

    @PostMapping("/create")
    public String doCreateProduct(
            @Validated(ValidationGroupSequence.class) @ModelAttribute("product") CreateProductFormData formData,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currencies", List.of(Currency.values()));
            model.addAttribute("measures", List.of(Measure.values()));
            model.addAttribute("categories", List.of(Category.values()));
            model.addAttribute("editMode", EditMode.CREAR);
            return "products/edit";
        }

        service.createProduct(formData.toParameters());
        redirectAttributes.addFlashAttribute("createdProductName", formData.getProductName() + " - " + formData.getProductBrand());
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String editProductForm(@PathVariable("id") UUID productId, Model model) {
        Product product = service.getProduct(productId)
                .orElseThrow(() -> new ObjectNotFoundException(productId, "Producto"));

        model.addAttribute("product", EditProductFormData.fromProduct(product));
        model.addAttribute("currencies", List.of(Currency.values()));
        model.addAttribute("measures", List.of(Measure.values()));
        model.addAttribute("categories", List.of(Category.values()));
        model.addAttribute("editMode", EditMode.ACTUALIZAR);
        return "products/edit";
    }

    @PostMapping("/{id}")
    public String doProductSupplier(@PathVariable("id") UUID productId,
            @Validated(ValidationGroupSequence.class) @ModelAttribute("product") EditProductFormData formData,
            BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        
        Product product = service.getProduct(productId)
                .orElseThrow(() -> new ObjectNotFoundException(productId, "Producto"));
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("currencies", List.of(Currency.values()));
            model.addAttribute("measures", List.of(Measure.values()));
            model.addAttribute("categories", List.of(Category.values()));
            model.addAttribute("editMode", EditMode.ACTUALIZAR);
            return "products/edit";
        }

        // Convertimos el usuario a EditUserFormData para comparar con el formulario
        EditProductFormData productDataFromDB = EditProductFormData.fromProduct(product);

        // Si los datos no cambiaron, redirigir sin actualizar
        if (productDataFromDB.equals(formData)) {
            return "redirect:/products";
        }

        // Si los datos cambiaron, actualizar y mandar mensaje
        service.editProduct(productId, formData.toParameters());
        redirectAttributes.addFlashAttribute("updatedProductName", formData.getProductName() + " - " + formData.getProductBrand());
        return "redirect:/suppliers";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteUser(@PathVariable("id") UUID productId, RedirectAttributes redirectAttributes) {
        Product product = service.getProduct(productId)
                .orElseThrow(() -> new ObjectNotFoundException(productId, "Producto"));
        service.deleteProduct(productId);
        redirectAttributes.addFlashAttribute("deletedProductName", product.getProductName() + " - " + product.getProductBrand());
        return "redirect:/suppliers";
    }
}
