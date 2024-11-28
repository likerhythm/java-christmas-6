package christmas.model.order;

import christmas.model.ErrorMessage;

import java.util.List;
import java.util.stream.Collectors;

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
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
        }
    }

    private static void validateAllDrink(List<Order> orderSheet) {
        boolean isAllDrink = orderSheet.stream()
                .allMatch(Order::isDrink);
        if (isAllDrink) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
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

    public List<OrderDetailDto> createOrderDetailDtos() {
        return orderSheet.stream()
                .map(Order::getOrderDetailDto)
                .collect(Collectors.toList());
    }
}
