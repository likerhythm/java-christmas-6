package christmas.model;

import java.time.LocalDate;

public class EventCalendar {

    private static final EventDates CHRISTMAS_DDAY_DISCOUNT_EVENT_DATES = EventDates.ofRange(1, 25);
    private static final EventDates EVENT_WEEKEND_EVENT_DATES = EventDates.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final EventDates STAR_STAMP_EVENT_DATES = EventDates.of(3, 10, 17, 24, 25, 31);

    public static boolean isChristmasDdayDiscountEventDay(LocalDate date) {
        return CHRISTMAS_DDAY_DISCOUNT_EVENT_DATES.isInclude(date);
    }

    public static boolean isEventWeekend(LocalDate date) {
        return EVENT_WEEKEND_EVENT_DATES.isInclude(date);
    }

    public static boolean isEventWeekday(LocalDate date) {
        return !EVENT_WEEKEND_EVENT_DATES.isInclude(date);
    }

    public static boolean isSpecialDiscountEventDay(LocalDate date) {
        return STAR_STAMP_EVENT_DATES.isInclude(date);
    }
}
