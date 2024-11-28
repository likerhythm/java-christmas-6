package christmas.model;

import christmas.model.event.EventDates;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventDatesTest {

    @Test
    void inRangeTest() {
        LocalDate date = LocalDate.of(2024, 12, 15);
        EventDates eventDates = EventDates.ofRange(1, 31);
        assertTrue(eventDates.isInclude(date));
    }
}
