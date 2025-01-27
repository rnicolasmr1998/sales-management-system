package com.salesmanagementsystem.sales_management_system.customer;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {
    private final CustomerRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    CustomerRepositoryTest(CustomerRepository repository,
                            JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSaveCustomer() {
        CustomerId id = repository.nextId();
        repository.save(new Customer(id, new FullName("Tommy", "Walton"),
                        Gender.HOMBRE, new PhoneNumber("959317763"),
                        true, LocalDate.now(),
                        null));

        entityManager.flush();

        UUID idInDb = jdbcTemplate.queryForObject("SELECT id FROM customer", UUID.class);
        assertThat(idInDb).isEqualTo(id.getId());
    }
}