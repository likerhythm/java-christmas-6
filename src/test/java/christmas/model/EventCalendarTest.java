package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventCalendarTest {

    @DisplayName("날짜가 주말인지 확인")
    @Test
    void isEventWeekendTest() {
        Assertions.assertTrue(EventCalendar.isEventWeekend(15));
    }

    @DisplayName("날짜가 평일인지 확인")
    @Test
    void isEventWeekDayTest() {
        Assertions.assertTrue(EventCalendar.isEventWeekDay(21));
    }

    @DisplayName("날짜가 특별할인 기간인지 확인")
    @Test
    void isSpecialDiscountEventDayTest() {
        Assertions.assertTrue(EventCalendar.isSpecialDiscountEventDay(24));
    }
}
