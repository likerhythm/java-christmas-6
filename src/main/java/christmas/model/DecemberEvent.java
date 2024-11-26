package christmas.model;

import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.Map;

public class DecemberEvent {

    private static final Map<String, String> GIVEAWAY = Map.of("샴페인", "1");
    private static final Map<String, String> EMPTY_GIVEAWAY = Map.of();
    private static final int GIVEAWAY_DISCOUNT = 25000;

    private boolean isValid = true;

    public void apply(int totalOrderCost) {
        if (totalOrderCost < 10000) {
            this.isValid = false;
        }
    }

    public int calculateChristmasDdayDiscount(LocalDate date) {
        if (EventCalendar.isChristmasDdayDiscountEventDay(date) && isValid) {
            return 1000 + (date.getDayOfMonth() - 1) * 100;
        }
        return 0;
    }

    public int calculateWeekdayDiscount(LocalDate date, int dessertCount) {
        if (EventCalendar.isEventWeekday(date) && isValid) {
            return dessertCount * 2023;
        }
        return 0;
    }

    public int calculateWeekendDiscount(LocalDate date, int mainMenuCount) {
        if (EventCalendar.isEventWeekend(date) && isValid) {
            return mainMenuCount * 2023;
        }
        return 0;
    }

    public int calculateSpecialDiscount(LocalDate date) {
        if (EventCalendar.isSpecialDiscountEventDay(date) && isValid) {
            return 1000;
        }
        return 0;
    }

    public int calculateGiveawayDiscount(int totalOrderCost) {
        if (totalOrderCost >= 120000) {
            return GIVEAWAY_DISCOUNT;
        }
        return 0;
    }

    public Map<String, String> getGiveaway(int totalOrderCost) {
        if (totalOrderCost >= 120000) {
            return GIVEAWAY;
        }
        return EMPTY_GIVEAWAY;
    }

    public void reset() {
        this.isValid = true;
    }
}
