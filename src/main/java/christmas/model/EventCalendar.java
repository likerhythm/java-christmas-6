package christmas.model;

import java.time.LocalDate;
import java.util.List;

public class EventCalendar {

    private static final List<Integer> EVENT_WEEKEND = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> STAR_STAMP = List.of(3, 10, 17, 24, 25, 31);

    public static boolean isEventWeekend(LocalDate date) {
        return EVENT_WEEKEND.contains(date.getDayOfMonth());
    }

    public static boolean isEventWeekday(LocalDate date) {
        return !EVENT_WEEKEND.contains(date.getDayOfMonth());
    }

    public static boolean isSpecialDiscountEventDay(LocalDate date) {
        return STAR_STAMP.contains(date.getDayOfMonth());
    }
}
