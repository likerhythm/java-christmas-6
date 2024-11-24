package christmas.model.event;

import christmas.model.DateRange;
import christmas.model.EventCalendar;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.Map;

public class DecemberEvent {

    private static final DateRange christmasDdayDiscountEvnetdateRange = DateRange.of(
            LocalDate.of(2024, 12, 1),
            LocalDate.of(2024, 12, 25)
    );

    private Orders orders;
    private boolean isValid = true;

    public DecemberEvent(Orders orders) {
        this.orders = orders;
        int totalOrderCost = orders.calculateTotalOrderCost();
        if (totalOrderCost < 10000) {
            this.isValid = false;
        }
    }

    public int calculateChristmasDdayDiscount(LocalDate date) {
        if (christmasDdayDiscountEvnetdateRange.isInRange(date) && isValid) {
            return 1000 + (date.getDayOfMonth() - 1) * 100;
        }
        return 0;
    }

    public int calculateWeekdayDiscount(LocalDate date) {
        if (EventCalendar.isEventWeekday(date) && isValid) {
            int dessertCount = orders.countDessert();
            return dessertCount * 2023;
        }
        return 0;
    }

    public int calculateWeekendDiscount(LocalDate date) {
        if (EventCalendar.isEventWeekend(date) && isValid) {
            int mainMenuCount = orders.countMainMenu();
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

    public Map<String, String> getGiveaway() {
        if (orders.calculateTotalOrderCost() >= 120000 && isValid) {
            return Map.of("샴페인", "1");
        }
        return Map.of();
    }
}
