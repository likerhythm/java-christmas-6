package christmas.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DateRangeTest {

    @Test
    void inRangeTest() {
        LocalDate now = LocalDate.now();
        DateRange dateRange = DateRange.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
        assertTrue(dateRange.inRange(now));
    }
}
