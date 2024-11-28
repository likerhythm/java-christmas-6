package christmas.model.order;

import christmas.model.ErrorMessage;
import christmas.model.menu.Menu;

import java.util.AbstractMap;
import java.util.Map;

public class Order {

    private static final int LOWER_LIMIT_OF_MENU_QUANTITY = 0;
    private final Menu menu;
    private final int quantity;

    private Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order of(Menu menu, int quantity) {
        if (quantity <= LOWER_LIMIT_OF_MENU_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
        }
        return new Order(menu, quantity);
    }

    public boolean isDrink() {
        return menu.isDrink();
    }

    public int calculateOrderCost() {
        return menu.calculateCost(quantity);
    }

    int getQuantity() {
        return this.quantity;
    }

    public int countDessert() {
        if (menu.isDessert()) {
            return quantity;
        }
        return 0;
    }

    public int countMainMenu() {
        if (menu.isMain()) {
            return quantity;
        }
        return 0;
    }

    public OrderDetailDto getOrderDetailDto() {
        return new OrderDetailDto(menu.getName(), String.valueOf(quantity));
    }
}
