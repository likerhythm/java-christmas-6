package christmas.model.order;

import christmas.model.menu.Menu;

public class Order {

    private final Menu menu;
    private final int quantity;

    private Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order of(Menu menu, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
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
}
