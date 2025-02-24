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
import com.salesmanagementsystem.sales_management_system.editions.EditCustomerFormData;
import com.salesmanagementsystem.sales_management_system.entities.Customer;
import com.salesmanagementsystem.sales_management_system.enums.EditMode;
import com.salesmanagementsystem.sales_management_system.enums.Gender;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
import com.salesmanagementsystem.sales_management_system.forms.CreateCustomerFormData;
import com.salesmanagementsystem.sales_management_system.services.CustomerService;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupSequence;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;
    private final UrlBuilder urlBuilder;

    public CustomerController(CustomerService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults({
                            @SortDefault("fullName.lastName"),
                            @SortDefault("fullName.firstName")}) Pageable pageable) {
        model.addAttribute("customers", service.getCustomers(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "customers/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("customer", new CreateCustomerFormData());
        model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
        model.addAttribute("editMode", EditMode.CREAR);
        return "customers/edit";
    }

    @PostMapping("/create")
    public String doCreateUser(
            @Validated(ValidationGroupSequence.class) @ModelAttribute("customer") CreateCustomerFormData formData,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
            model.addAttribute("editMode", EditMode.CREAR);
            return "customers/edit";
        }

        service.createCustomer(formData.toParameters());
        redirectAttributes.addFlashAttribute("createdCustomerName", formData.getFirstName() + " " + formData.getLastName());
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String editCustomerForm(@PathVariable("id") UUID customerId, Model model) {
        Customer customer = service.getCustomer(customerId)
                .orElseThrow(() -> new ObjectNotFoundException(customerId, "Cliente"));

        model.addAttribute("customer", EditCustomerFormData.fromCustomer(customer));
        model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
        model.addAttribute("editMode", EditMode.ACTUALIZAR);
        return "customers/edit";
    }

    @PostMapping("/{id}")
    public String doEditCustomer(@PathVariable("id") UUID customerId,
            @Validated(ValidationGroupSequence.class) @ModelAttribute("customer") EditCustomerFormData formData,
            BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        Customer customer = service.getCustomer(customerId)
                .orElseThrow(() -> new ObjectNotFoundException(customerId, "Cliente"));
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
            model.addAttribute("editMode", EditMode.ACTUALIZAR);
            return "customers/edit";
        }

        // Convertimos el usuario a EditUserFormData para comparar con el formulario
        EditCustomerFormData customerDataFromDB = EditCustomerFormData.fromCustomer(customer);

        // Si los datos no cambiaron, redirigir sin actualizar
        if (customerDataFromDB.equals(formData)) {
            return "redirect:/customers";
        }

        // Si los datos cambiaron, actualizar y mandar mensaje
        service.editCustomer(customerId, formData.toParameters());
        redirectAttributes.addFlashAttribute("updatedCustomerName", customer.getFullName().getFullName());
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteUser(@PathVariable("id") UUID customerId, RedirectAttributes redirectAttributes) {
        Customer customer = service.getCustomer(customerId)
                .orElseThrow(() -> new ObjectNotFoundException(customerId, "Cliente"));
        service.deleteCustomer(customerId);
        redirectAttributes.addFlashAttribute("deletedCustomerName", customer.getFullName().getFullName());
        return "redirect:/customers";
    }

}
