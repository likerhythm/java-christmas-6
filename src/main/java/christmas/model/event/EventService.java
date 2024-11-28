package christmas.model.event;

import christmas.dto.DiscountDetailsDto;
import christmas.model.menu.Menu;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.Map;

public class EventService {

    private static final Map<String, String> GIVEAWAY = Map.of(Menu.CHAMPAGNE.getName(), "1");
    private static final Map<String, String> EMPTY_GIVEAWAY = Map.of();
    private static final int NO_DISCOUNT = 0;
    private static final int YEAR_DISCOUNT = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int GIVEAWAY_DISCOUNT = 25000;
    private static final int LOWER_LIMIT_TOTAL_ORDER_COST = 10000;
    private static final int LOWER_LIMIT_GIVEAWAY_DISCOUNT = 10000;
    private static final int CHRISTMAS_DDAY_BASE_DISCOUNT = 1000;
    private static final int CHRISTMAS_DDAY_INCREMENT_DISCOUNT = 100;

    private boolean isValid = true;
    private Orders orders;
    private LocalDate date;

    public void apply(Orders orders, LocalDate date) {
        int totalOrderCost = orders.calculateTotalOrderCost();
        if (totalOrderCost < LOWER_LIMIT_TOTAL_ORDER_COST) {
            isValid = false;
        }
        this.orders = orders;
        this.date = date;
    }

    public Map<String, String> getGiveaway() {
        if (orders.calculateTotalOrderCost() >= LOWER_LIMIT_GIVEAWAY_DISCOUNT) {
            return GIVEAWAY;
        }
        return EMPTY_GIVEAWAY;
    }

    public DiscountDetailsDto createDiscountDetailsDto() {
        DiscountDetailsDto discountDetailsDto = new DiscountDetailsDto();
        discountDetailsDto.addDetail(calculateChristmasDdayDiscount());
        discountDetailsDto.addDetail(calculateWeekdayDiscount());
        discountDetailsDto.addDetail(calculateWeekendDiscount());
        discountDetailsDto.addDetail(calculateSpecialDiscount());
        discountDetailsDto.addDetail(calculateGiveawayDiscount());

        return discountDetailsDto;
    }

    public int calculateTotalDiscount() {
        return calculateChristmasDdayDiscount() // 총 혜택 금액
                + calculateWeekdayDiscount()
                + calculateWeekendDiscount()
                + calculateSpecialDiscount()
                + calculateGiveawayDiscount();
    }

    public void reset() {
        isValid = true;
    }

    private int calculateChristmasDdayDiscount() {
        if (EventCalendar.isChristmasDdayDiscountEventDay(date) && isValid) {
            return CHRISTMAS_DDAY_BASE_DISCOUNT + (date.getDayOfMonth() - 1) * CHRISTMAS_DDAY_INCREMENT_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    private int calculateWeekdayDiscount() {
        if (EventCalendar.isEventWeekday(date) && isValid) {
            return orders.countDessert() * YEAR_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    private int calculateWeekendDiscount() {
        if (EventCalendar.isEventWeekend(date) && isValid) {
            return orders.countMainMenu() * YEAR_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    private int calculateSpecialDiscount() {
        if (EventCalendar.isSpecialDiscountEventDay(date) && isValid) {
            return SPECIAL_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    private int calculateGiveawayDiscount() {
        if (orders.calculateTotalOrderCost() >= LOWER_LIMIT_GIVEAWAY_DISCOUNT) {
            return GIVEAWAY_DISCOUNT;
        }
        return NO_DISCOUNT;
    }
}
