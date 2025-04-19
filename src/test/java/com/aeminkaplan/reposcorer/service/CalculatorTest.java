package com.aeminkaplan.reposcorer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculatorTest {

    @Autowired
    private Calculator calculator;


    @Test
    void testCalculateUpdatedRepoScore() {
        long score = calculator.calculate(5,5, LocalDateTime.now());
        assertEquals(20, score);
    }
    @Test
    void testCalculateLastMonthUpdatedRepoScore() {
        long score = calculator.calculate(5,5, LocalDateTime.now().minusMonths(2));
        assertEquals(15, score);
    }
    @Test
    void testCalculateStaleRepoScore() {
        long score = calculator.calculate(5,5, LocalDateTime.now().minusYears(2));
        assertEquals(10, score);
    }
}