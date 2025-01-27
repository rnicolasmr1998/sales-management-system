package com.salesmanagementsystem.sales_management_system.user;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.salesmanagementsystem.sales_management_system.embbedables.Email;
import com.salesmanagementsystem.sales_management_system.embbedables.FullName;
import com.salesmanagementsystem.sales_management_system.embbedables.Gender;
import com.salesmanagementsystem.sales_management_system.embbedables.PhoneNumber;

import io.github.wimdeblauwe.jpearl.InMemoryUniqueIdGenerator;
import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
class UserRepositoryTest {
    private final UserRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepositoryTest(UserRepository repository,
                        JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSaveUser() {
        UserId id = repository.nextId();
        repository.save(new User(id, new FullName("Tommy", "Walton"),
                                Gender.HOMBRE,
                                LocalDate.of(2001, Month.FEBRUARY, 17),
                                new Email("tommy.walton@gmail.com"),
                                new PhoneNumber("959317763"),
                                true,
                                LocalDate.now(),
                                null));

        entityManager.flush();
        assertThat(jdbcTemplate.queryForObject("SELECT id FROM usuario", UUID.class)).isEqualTo(id.getId());
        assertThat(jdbcTemplate.queryForObject("SELECT nombre FROM usuario", String.class)).isEqualTo("Tommy");
        assertThat(jdbcTemplate.queryForObject("SELECT apellido FROM usuario", String.class)).isEqualTo("Walton");
        assertThat(jdbcTemplate.queryForObject("SELECT genero FROM usuario", Gender.class)).isEqualTo(Gender.HOMBRE);
        assertThat(jdbcTemplate.queryForObject("SELECT fecha_nacimiento FROM usuario", LocalDate.class)).isEqualTo("2001-02-17");
        assertThat(jdbcTemplate.queryForObject("SELECT correo_electronico FROM usuario", String.class)).isEqualTo("tommy.walton@gmail.com");
        assertThat(jdbcTemplate.queryForObject("SELECT numero_celular FROM usuario", String.class)).isEqualTo("959317763");
        assertThat(jdbcTemplate.queryForObject("SELECT estado_usuario FROM usuario", Boolean.class)).isEqualTo(true);
        assertThat(jdbcTemplate.queryForObject("SELECT fecha_registro FROM usuario", LocalDate.class)).isEqualTo(LocalDate.now());
        assertThat(jdbcTemplate.queryForObject("SELECT fecha_eliminacion FROM usuario", LocalDate.class)).isNull();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> uniqueIdGenerator() { //<.>
            return new InMemoryUniqueIdGenerator();
        }
    }
}