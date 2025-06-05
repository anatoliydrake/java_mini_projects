package com.anatoliydrake.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {
    private FibonacciService service;
    @Mock
    private FibonacciRepository repository;
    @Mock
    private FibonacciCalculator calculator;

    @BeforeEach
    public void setUp() {
        service = new FibonacciService(repository, calculator);
    }

    @Test
    @DisplayName("Value is in Database")
    public void testFibonacciNumberWithValueInDatabase() {
        when(repository.findByIndex(10)).thenReturn(Optional.of(new FibonacciNumber(10, 55)));

        FibonacciNumber number = service.fibonacciNumber(10);
        assertEquals(55, number.getValue());
        verify(repository, times(1)).findByIndex(10);
        verify(repository, times(0)).save(eq(number));
        verify(calculator, times(0)).getFibonacciNumber(10);
    }

    @Test
    @DisplayName("Value is not in Database")
    public void testFibonacciNumberWithValueNotInDatabase() {
        when(repository.findByIndex(10)).thenReturn(Optional.empty());
        when(calculator.getFibonacciNumber(10)).thenReturn(55);

        FibonacciNumber number = service.fibonacciNumber(10);
        assertEquals(55, number.getValue());
        verify(repository, times(1)).findByIndex(10);
        verify(calculator, times(1)).getFibonacciNumber(10);
        verify(repository).save(eq(number));
    }

    @Test
    @DisplayName("Index less than 1")
    public void testFibonacciNumberWithIndex0() {
        assertThrows(IllegalArgumentException.class, () -> service.fibonacciNumber(-1));
    }
}
