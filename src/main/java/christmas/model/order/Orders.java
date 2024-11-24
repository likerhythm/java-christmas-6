package christmas.model.order;

import java.util.List;

public class Orders {

    private final List<Order> orderSheet;

    private Orders(List<Order> orderSheet) {
        this.orderSheet = orderSheet;
    }

    public static Orders of(List<Order> orderSheet) {
        int totalMenuQuantity = orderSheet.stream()
                .mapToInt(Order::getQuantity)
                .sum();
        validateMaxMenuCount(totalMenuQuantity);
        validateAllDrink(orderSheet);
        return new Orders(orderSheet);
    }

    public int calculateTotalOrderCost() {
        return orderSheet.stream()
                .mapToInt(Order::calculateOrderCost)
                .sum();
    }

    private static void validateMaxMenuCount(int totalMenuQuantity) {
        if (totalMenuQuantity > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateAllDrink(List<Order> orderSheet) {
        boolean isAllDrink = orderSheet.stream()
                .allMatch(Order::isDrink);
        if (isAllDrink) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int countDessert() {
        return orderSheet.stream()
                .mapToInt(Order::countDessert)
                .sum();
    }

    public int countMainMenu() {
        return orderSheet.stream()
                .mapToInt(Order::countMainMenu)
                .sum();
    }
}
