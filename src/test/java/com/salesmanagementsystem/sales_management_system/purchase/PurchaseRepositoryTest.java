package com.salesmanagementsystem.sales_management_system.purchase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PurchaseRepositoryTest {
    private final PurchaseRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PurchaseRepositoryTest(PurchaseRepository repository,
                           JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSavePurchase() {
        PurchaseId id = repository.nextId();
        repository.save(new Purchase());

        entityManager.flush();

        UUID idInDb = jdbcTemplate.queryForObject("SELECT id FROM purchase", UUID.class);
        assertThat(idInDb).isEqualTo(id.getId());
    }
}