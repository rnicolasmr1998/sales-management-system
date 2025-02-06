package com.salesmanagementsystem.sales_management_system;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.salesmanagementsystem.sales_management_system.embbedables.Category;
import com.salesmanagementsystem.sales_management_system.embbedables.Currency;
import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.Measure;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.forms.CreateCustomerParameters;
import com.salesmanagementsystem.sales_management_system.forms.CreateProductParameters;
import com.salesmanagementsystem.sales_management_system.forms.CreateUserParameters;
import com.salesmanagementsystem.sales_management_system.services.CustomerService;
import com.salesmanagementsystem.sales_management_system.services.ProductService;
import com.salesmanagementsystem.sales_management_system.services.UserService;

@Component
@Profile("init-db")
public class DatabaseInitializer implements CommandLineRunner {
    private final Faker faker = new Faker();
    private final UserService userService;
    private final CustomerService customerService;
    private final ProductService productService;

    public DatabaseInitializer(UserService userService, CustomerService customerService, ProductService productService) {
        this.userService = userService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public void run(String ... args) {
        for (int i = 0; i < 20; i++) {
            CreateUserParameters userParameters = newRandomUserParameters();
            CreateCustomerParameters customerParameters = newRandomCustomerParameters();
            CreateProductParameters productParameters = newRandomProductParameters();
            userService.createUser(userParameters);
            customerService.createCustomer(customerParameters);
            productService.createProduct(productParameters);
        }
    }

    private CreateUserParameters newRandomUserParameters() {
        Name name = faker.name();
        FullName fullName = new FullName(name.firstName(), name.lastName());
        Gender gender = faker.bool().bool() ? Gender.HOMBRE : Gender.MUJER;
        LocalDate birthday = LocalDate.ofInstant(faker.date().birthday(10, 40).toInstant(), ZoneId.systemDefault());
        Email email = new Email(faker.internet().emailAddress(generateEmailLocalPart(fullName)));
        PhoneNumber phoneNumber = new PhoneNumber("9"+ faker.number().digits(8));
        return new CreateUserParameters(fullName, gender, birthday, email, phoneNumber);
    }

    private CreateCustomerParameters newRandomCustomerParameters() {
        Name name = faker.name();
        FullName fullName = new FullName(name.firstName(), name.lastName());
        Gender gender = faker.bool().bool() ? Gender.HOMBRE : Gender.MUJER;
        PhoneNumber phoneNumber = new PhoneNumber("9"+ faker.number().digits(8));
        BigDecimal debt = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
        return new CreateCustomerParameters(fullName, gender, phoneNumber, debt);
    }

    private CreateProductParameters newRandomProductParameters() {
        String productName = faker.commerce().productName();
        String productBrand = faker.commerce().material();
        String productDescription = faker.lorem().sentence();
        BigDecimal purchasePriceUpdated = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
        Currency currency = faker.bool().bool() ? Currency.DOLARES : Currency.SOLES;
        Double availableStock = Double.valueOf(faker.number().numberBetween(0, 1000));
        Measure measure = faker.options().option(Measure.class);
        Category category = faker.bool().bool() ? Category.GRIFERIA : Category.ELECTRICO;
        return new CreateProductParameters(productName, productBrand, productDescription, purchasePriceUpdated, currency, availableStock, measure, category);
    }

    private String generateEmailLocalPart(FullName fullName) {
        return String.format("%s.%s",
                            StringUtils.remove(fullName.getFirstName().toLowerCase(),"'"),
                            StringUtils.remove(fullName.getLastName().toLowerCase(),"'"));
        }

}
