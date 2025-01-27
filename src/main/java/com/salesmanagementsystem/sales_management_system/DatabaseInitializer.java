package com.salesmanagementsystem.sales_management_system;

import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;
import com.salesmanagementsystem.sales_management_system.user.CreateUserParameters;
import com.salesmanagementsystem.sales_management_system.user.UserService;

@Component
@Profile("init-db")
public class DatabaseInitializer implements CommandLineRunner{
    private final Faker faker = new Faker();
    private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 20; i++) {
            CreateUserParameters parameters = newRandomUserParameters();
            userService.createUser(parameters);
        }
    }

    private CreateUserParameters newRandomUserParameters() {
        Name name = faker.name();
        FullName fullName = new FullName(name.firstName(), name.lastName());
        Gender gender = faker.bool().bool() ? Gender.HOMBRE : Gender.MUJER;
        LocalDate birthday = LocalDate.ofInstant(faker.date().birthday(10, 40).toInstant(), ZoneId.systemDefault());
        Email email = new Email(faker.internet().emailAddress(generateEmailLocalPart(fullName)));
        PhoneNumber phoneNumber = new PhoneNumber(faker.phoneNumber().phoneNumber());
        Boolean userStatus = faker.bool().bool();
        LocalDate registrationDate = LocalDate.now();
        LocalDate deleteDate = null;
        return new CreateUserParameters(fullName, gender, birthday, email, phoneNumber, userStatus, registrationDate, deleteDate);
    }
    
    private String generateEmailLocalPart(FullName fullName) {
        return "%s.%s".formatted(
                StringUtils.remove(fullName.getFirstName().toLowerCase(), "'"),
                StringUtils.remove(fullName.getLastName().toLowerCase(), "'"));
    }
}
