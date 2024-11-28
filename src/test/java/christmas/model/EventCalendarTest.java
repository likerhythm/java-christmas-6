package christmas.model;

import christmas.model.event.EventCalendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventCalendarTest {

    @DisplayName("날짜가 주말인지 확인")
    @Test
    void isEventWeekendTest() {
        Assertions.assertTrue(EventCalendar.isEventWeekend(LocalDate.of(2024, 12, 15)));
    }

    @DisplayName("날짜가 평일인지 확인")
    @Test
    void isEventWeekDayTest() {
        Assertions.assertTrue(EventCalendar.isEventWeekday(LocalDate.of(2024, 12, 21)));
    }

    @DisplayName("날짜가 특별할인 기간인지 확인")
    @Test
    void isSpecialDiscountEventDayTest() {
        Assertions.assertTrue(EventCalendar.isSpecialDiscountEventDay(LocalDate.of(2024, 12, 24)));
    }
}
