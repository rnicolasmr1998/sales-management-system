package com.salesmanagementsystem.sales_management_system.controllers;

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
import com.salesmanagementsystem.sales_management_system.editions.EditSupplierFormData;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.enums.EditMode;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
import com.salesmanagementsystem.sales_management_system.forms.CreateSupplierFormData;
import com.salesmanagementsystem.sales_management_system.services.SupplierService;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupSequence;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService service;
    private final UrlBuilder urlBuilder;

    public SupplierController(SupplierService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault("supplierName") Pageable pageable) {
        model.addAttribute("suppliers", service.getSuppliers(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "suppliers/list";
    }

    @GetMapping("/create")
    public String createSupplierForm(Model model) {
        model.addAttribute("supplier", new CreateSupplierFormData());
        model.addAttribute("editMode", EditMode.CREAR);
        return "suppliers/edit";
    }

    @PostMapping("/create")
    public String doCreateSupplier(
            @Validated(ValidationGroupSequence.class) @ModelAttribute("supplier") CreateSupplierFormData formData,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", EditMode.CREAR);
            return "suppliers/edit";
        }

        service.createSupplier(formData.toParameters());
        redirectAttributes.addFlashAttribute("createdSupplierName", formData.getSupplierName());
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}")
    public String editSupplierForm(@PathVariable("id") UUID supplierId, Model model) {
        Supplier supplier = service.getSupplier(supplierId)
                .orElseThrow(() -> new ObjectNotFoundException(supplierId, "Proveedor"));

        model.addAttribute("supplier", EditSupplierFormData.fromSupplier(supplier));
        model.addAttribute("editMode", EditMode.ACTUALIZAR);
        return "suppliers/edit";
    }

    @PostMapping("/{id}")
    public String doEditSupplier(@PathVariable("id") UUID supplierId,
            @Validated(ValidationGroupSequence.class) @ModelAttribute("supplier") EditSupplierFormData formData,
            BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        Supplier supplier = service.getSupplier(supplierId)
                .orElseThrow(() -> new ObjectNotFoundException(supplierId, "Proveedor"));
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", EditMode.ACTUALIZAR);
            return "suppliers/edit";
        }

        // Convertimos el usuario a EditUserFormData para comparar con el formulario
        EditSupplierFormData supplierDataFromDB = EditSupplierFormData.fromSupplier(supplier);

        // Si los datos no cambiaron, redirigir sin actualizar
        if (supplierDataFromDB.equals(formData)) {
            return "redirect:/suppliers";
        }

        // Si los datos cambiaron, actualizar y mandar mensaje
        service.editSupplier(supplierId, formData.toParameters());
        redirectAttributes.addFlashAttribute("updatedSupplierName", supplier.getSupplierName());
        return "redirect:/suppliers";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteUser(@PathVariable("id") UUID supplierId, RedirectAttributes redirectAttributes) {
        Supplier supplier = service.getSupplier(supplierId)
                .orElseThrow(() -> new ObjectNotFoundException(supplierId, "Proveedor"));
        service.deleteSupplier(supplierId);
        redirectAttributes.addFlashAttribute("deletedSupplierName", supplier.getSupplierName());
        return "redirect:/suppliers";
    }
}
