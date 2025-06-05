package com.anatoliydrake.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    FibonacciRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Saving test")
    public void testSave() {
        FibonacciNumber number = new FibonacciNumber(10, 55);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(number.getValue(), actual.get(0).getValue());
    }

    @Test
    @DisplayName("Getting test")
    public void testFindById() {
        FibonacciNumber number = new FibonacciNumber(10, 55);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);

        FibonacciNumber loadedNumber = repository.findByIndex(10).orElseThrow();

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(loadedNumber.getValue(), actual.get(0).getValue());
    }

    @Test
    @DisplayName("Repeated insert test")
    public void testSaveAgain() {
        FibonacciNumber number = new FibonacciNumber(10, 55);
        repository.save(number);
        entityManager.flush();
        repository.save(number);
        entityManager.flush();

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 10",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(1, actual.size());
        assertDoesNotThrow(() -> repository.save(number));
    }
}
