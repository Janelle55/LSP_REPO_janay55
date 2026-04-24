package org.howard.edu.lsp.finalexam.question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for GradeCalculator.
 */
class GradeCalculatorTest {

    @Test
    void testAverage() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals(85.0, calculator.average(80, 85, 90));
    }

    @Test
    void testLetterGradeA() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void testLetterGradeB() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals("B", calculator.letterGrade(85));
    }

    @Test
    void testLetterGradeC() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void testLetterGradeD() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals("D", calculator.letterGrade(65));
    }

    @Test
    void testLetterGradeF() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals("F", calculator.letterGrade(50));
    }

    @Test
    void testIsPassing() {
        GradeCalculator calculator = new GradeCalculator();
        assertTrue(calculator.isPassing(70));
        assertFalse(calculator.isPassing(59));
    }

    @Test
    void testBoundaryMinimumScore() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals(0.0, calculator.average(0, 0, 0));
    }

    @Test
    void testBoundaryMaximumScore() {
        GradeCalculator calculator = new GradeCalculator();
        assertEquals(100.0, calculator.average(100, 100, 100));
    }

    @Test
    void testNegativeScoreThrowsException() {
        GradeCalculator calculator = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.average(-1, 80, 90));
    }

    @Test
    void testScoreAbove100ThrowsException() {
        GradeCalculator calculator = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.average(101, 80, 90));
    }
}