package com.anatoliydrake.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibonacciCalculatorTest {

    private FibonacciCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new FibonacciCalculator();
    }

    @ParameterizedTest
    @DisplayName("Test correct index")
    @CsvSource({"1,1", "2,1", "3,2", "4,3", "5,5", "6,8", "10,55"})
    public void testGetFibonacciNumberWithIndex10(int index, int value) {
        assertEquals(value, calculator.getFibonacciNumber(index));
    }

    @Test
    @DisplayName("Test index less than 1")
    public void testGetFibonacciNumberWithIndexLessThanOne() {
        assertThrows(IllegalArgumentException.class, () ->  calculator.getFibonacciNumber(0));
    }

}
