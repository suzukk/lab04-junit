package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCalculatorTest {

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(95.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void eightyFiveIsB() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(85.0);
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void seventyFiveIsC() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(75.0);
        assertEquals("C", grade);
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void sixtyFiveIsD() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(65.0);
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("-1 оноо оруулахад IllegalArgumentException үүсэх ёстой")
    void negativeScoreThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1.0)
        );
    }

    @Test
    @DisplayName("101 оноо оруулахад IllegalArgumentException үүсэх ёстой")
    void scoreAbove100ThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101.0)
        );
    }

    @ParameterizedTest
    @DisplayName("Дүнгийн хязгаарын утгууд зөв ангилагдах ёстой")
    @CsvSource({
            "95, A",
            "30, F",
            "90, A",
            "89.99, B",
            "80, B",
            "70, C",
            "60, D",
            "59.99, F",
            "0, F",
            "100, A"
    })
    void letterGradeBoundaries(double score, String expected) {
        GradeCalculator calc = new GradeCalculator();
        String actual = calc.letterGrade(score);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Бүх дээд оноог авсан үед нийт 100 оноо гарах ёстой")
    void totalScoreShouldBe100() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total);
    }

    @Test
    @DisplayName("Ирц сөрөг үед IllegalArgumentException үүсэх ёстой")
    void negativeAttendanceThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Лабораторийн оноо 40-өөс их үед IllegalArgumentException үүсэх ёстой")
    void labAboveMaximumThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }

    @ParameterizedTest
    @DisplayName("Нийт оноо зөв нийлбэрлэгдэх ёстой")
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "10, 30, 10, 10, 20, 80",
            "5, 20, 5, 5, 15, 50",
            "0, 0, 0, 0, 0, 0"
    })
    void totalScoreCalculation(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        GradeCalculator calc = new GradeCalculator();
        double actual = calc.totalScore(att, lab, quiz1, quiz2, exam);
        assertEquals(expected, actual);
    }
}

