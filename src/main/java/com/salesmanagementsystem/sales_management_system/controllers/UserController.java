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
import com.salesmanagementsystem.sales_management_system.editions.EditUserFormData;
import com.salesmanagementsystem.sales_management_system.entities.User;
import com.salesmanagementsystem.sales_management_system.enums.EditMode;
import com.salesmanagementsystem.sales_management_system.enums.Gender;
import com.salesmanagementsystem.sales_management_system.exceptions.ObjectNotFoundException;
import com.salesmanagementsystem.sales_management_system.forms.CreateUserFormData;
import com.salesmanagementsystem.sales_management_system.services.UserService;
import com.salesmanagementsystem.sales_management_system.validations.ValidationGroupSequence;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UrlBuilder urlBuilder;

    public UserController(UserService service, UrlBuilder urlBuilder) {
        this.service = service;
        this.urlBuilder = urlBuilder;
    }

    @GetMapping
    public String index(Model model,
            @SortDefault.SortDefaults({
                    @SortDefault("fullName.lastName"),
                    @SortDefault("fullName.firstName") }) Pageable pageable) {
        model.addAttribute("users", service.getUsers(pageable));
        model.addAttribute("urlBuilder", urlBuilder);
        return "users/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new CreateUserFormData());
        model.addAttribute("genders", List.of(Gender.values()));
        model.addAttribute("editMode", EditMode.CREAR);
        return "users/edit";
    }

    @PostMapping("/create")
    public String doCreateUser(
            @Validated(ValidationGroupSequence.class) @ModelAttribute("user") CreateUserFormData formData,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
            model.addAttribute("editMode", EditMode.CREAR);
            return "users/edit";
        }

        service.createUser(formData.toParameters());
        redirectAttributes.addFlashAttribute("createdUserName", formData.getFirstName() + " " + formData.getLastName());
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String editUserForm(@PathVariable("id") UUID userId, Model model) {
        User user = service.getUser(userId)
                .orElseThrow(() -> new ObjectNotFoundException(userId, "Usuario"));

        model.addAttribute("user", EditUserFormData.fromUser(user));
        model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
        model.addAttribute("editMode", EditMode.ACTUALIZAR);
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String doEditUser(@PathVariable("id") UUID userId,
            @Validated(ValidationGroupSequence.class) @ModelAttribute("user") EditUserFormData formData,
            BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        User user = service.getUser(userId)
                .orElseThrow(() -> new ObjectNotFoundException(userId, "Usuario"));
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.HOMBRE, Gender.MUJER, Gender.OTRO));
            model.addAttribute("editMode", EditMode.ACTUALIZAR);
            return "users/edit";
        }

        // Convertimos el usuario a EditUserFormData para comparar con el formulario
        EditUserFormData userDataFromDB = EditUserFormData.fromUser(user);

        // Si los datos no cambiaron, redirigir sin actualizar
        if (userDataFromDB.equals(formData)) {
            return "redirect:/users";
        }

        // Si los datos cambiaron, actualizar y mandar mensaje
        service.editUser(userId, formData.toParameters());
        redirectAttributes.addFlashAttribute("updatedUserName", user.getFullName().getFullName());
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteUser(@PathVariable("id") UUID userId, RedirectAttributes redirectAttributes) {
        User user = service.getUser(userId)
                .orElseThrow(() -> new ObjectNotFoundException(userId, "Usuario"));
        service.deleteUser(userId);
        redirectAttributes.addFlashAttribute("deletedUserName", user.getFullName().getFullName());
        return "redirect:/users";
    }

}