package christmas.model;

import christmas.model.event.EventDates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventDatesTest {

    @DisplayName("날짜가 기간에 속하는지 확인")
    @Test
    void inRangeTest() {
        LocalDate date = LocalDate.of(2024, 12, 15);
        EventDates eventDates = EventDates.ofRange(1, 31);
        assertTrue(eventDates.isInclude(date));
    }
}
