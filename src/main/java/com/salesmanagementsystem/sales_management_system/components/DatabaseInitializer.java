package com.salesmanagementsystem.sales_management_system.components;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

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
import com.salesmanagementsystem.sales_management_system.embbedables.PaymentMethod;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreateCustomerParameters;
import com.salesmanagementsystem.sales_management_system.parameters.CreatePaymentSupplierParameters;
import com.salesmanagementsystem.sales_management_system.parameters.CreateProductParameters;
import com.salesmanagementsystem.sales_management_system.parameters.CreateSupplierParameters;
import com.salesmanagementsystem.sales_management_system.parameters.CreateUserParameters;
import com.salesmanagementsystem.sales_management_system.repositories.SupplierRepository;
import com.salesmanagementsystem.sales_management_system.services.CustomerService;
import com.salesmanagementsystem.sales_management_system.services.PaymentSupplierService;
import com.salesmanagementsystem.sales_management_system.services.ProductService;
import com.salesmanagementsystem.sales_management_system.services.SupplierService;
import com.salesmanagementsystem.sales_management_system.services.UserService;

@Component
@Profile("init-db")
public class DatabaseInitializer implements CommandLineRunner {
    private final Faker faker = new Faker();
    private final UserService userService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final SupplierService supplierService;
    private final PaymentSupplierService paymentSupplierService;
    private final SupplierRepository supplierRepository;

    public DatabaseInitializer(UserService userService, CustomerService customerService, ProductService productService,
            SupplierService supplierService, PaymentSupplierService paymentSupplierService,
            SupplierRepository supplierRepository) {
        this.userService = userService;
        this.customerService = customerService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.paymentSupplierService = paymentSupplierService;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 20; i++) {
            CreateUserParameters userParameters = newRandomUserParameters();
            CreateCustomerParameters customerParameters = newRandomCustomerParameters();
            CreateProductParameters productParameters = newRandomProductParameters();
            CreateSupplierParameters supplierParameters = newRandomSupplierParameters();
            CreatePaymentSupplierParameters paymentSupplierParameters = newRandomPaymentSupplierParameters();
            userService.createUser(userParameters);
            customerService.createCustomer(customerParameters);
            productService.createProduct(productParameters);
            supplierService.createSupplier(supplierParameters);
            paymentSupplierService.createPaymentSupplier(paymentSupplierParameters);
        }
    }

    private CreateUserParameters newRandomUserParameters() {
        Name name = faker.name();
        FullName fullName = new FullName(name.firstName(), name.lastName());
        Gender gender = faker.options().option(Gender.class);
        LocalDate birthday = LocalDate.ofInstant(faker.date().birthday(10, 40).toInstant(), ZoneId.systemDefault());
        Email email = new Email(faker.internet().emailAddress(generateEmailLocalPart(fullName)));
        PhoneNumber phoneNumber = new PhoneNumber("9" + faker.number().digits(8));
        return new CreateUserParameters(fullName, gender, birthday, email, phoneNumber);
    }

    private CreateCustomerParameters newRandomCustomerParameters() {
        Name name = faker.name();
        FullName fullName = new FullName(name.firstName(), name.lastName());
        Gender gender = faker.options().option(Gender.class);
        PhoneNumber phoneNumber = new PhoneNumber("9" + faker.number().digits(8));
        BigDecimal debt = BigDecimal.valueOf(faker.number().numberBetween(6000, 10000));
        return new CreateCustomerParameters(fullName, gender, phoneNumber, debt);
    }

    private CreateProductParameters newRandomProductParameters() {
        String productName = faker.commerce().productName();
        String productBrand = faker.commerce().material();
        String productDescription = faker.lorem().sentence();
        BigDecimal purchasePriceUpdated = BigDecimal.valueOf(faker.number().numberBetween(5, 100));
        Currency currency = faker.options().option(Currency.class);
        BigDecimal availableStock = BigDecimal.valueOf(faker.number().numberBetween(1, 500));
        Measure measure = faker.options().option(Measure.class);
        Category category = faker.options().option(Category.class);
        return new CreateProductParameters(productName, productBrand, productDescription, purchasePriceUpdated,
                currency, availableStock, measure, category);
    }

    private CreateSupplierParameters newRandomSupplierParameters() {
        String ruc = faker.number().digits(11);
        String supplierName = faker.commerce().productName();
        PhoneNumber phoneNumber = new PhoneNumber("9" + faker.number().digits(8));
        BigDecimal supplierDebtSoles = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
        BigDecimal supplierDebtDollars = BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
        return new CreateSupplierParameters(ruc, supplierName, phoneNumber, supplierDebtSoles, supplierDebtDollars);
    }

    private CreatePaymentSupplierParameters newRandomPaymentSupplierParameters() {
        BigDecimal amount = BigDecimal.valueOf(faker.number().numberBetween(5, 100));
        Currency currency = faker.options().option(Currency.class);
        PaymentMethod paymentMethod = faker.options().option(PaymentMethod.class);
        String note = faker.lorem().sentence();
        List<UUID> productIds = supplierRepository.findAll().stream().map(Supplier::getSupplierId)
                .collect(Collectors.toList());
        UUID supplierId = productIds.get(new Random().nextInt(productIds.size()));
        return new CreatePaymentSupplierParameters(amount, currency, paymentMethod, note, supplierId);
    }

    private String generateEmailLocalPart(FullName fullName) {
        return String.format("%s.%s",
                StringUtils.remove(fullName.getFirstName().toLowerCase(), "'"),
                StringUtils.remove(fullName.getLastName().toLowerCase(), "'"));
    }
}
