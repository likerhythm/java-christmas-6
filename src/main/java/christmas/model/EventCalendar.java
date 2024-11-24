package christmas.model;

import java.util.List;

public class EventCalendar {

    private static final List<Integer> EVENT_WEEKEND = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> STAR_STAMP = List.of(3, 10, 17, 24, 25, 31);

    public static boolean isEventWeekend(int date) {
        return EVENT_WEEKEND.contains(date);
    }

    public static boolean isEventWeekDay(int date) {
        return !EVENT_WEEKEND.contains(date);
    }

    public static boolean isSpecialDiscountEventDay(int date) {
        return STAR_STAMP.contains(date);
    }
}
