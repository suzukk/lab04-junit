package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    void shouldReturnAFor90() {
        assertEquals("A", calculator.calculateGrade(90));
    }

    @Test
    void shouldReturnBFor80() {
        assertEquals("B", calculator.calculateGrade(80));
    }

    @Test
    void shouldReturnCFor70() {
        assertEquals("C", calculator.calculateGrade(70));
    }

    @Test
    void shouldReturnDFor60() {
        assertEquals("D", calculator.calculateGrade(60));
    }

    @Test
    void shouldReturnFFor59() {
        assertEquals("F", calculator.calculateGrade(59));
    }
}
