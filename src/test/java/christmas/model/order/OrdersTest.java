package christmas.model.order;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrdersTest {

    @DisplayName("주문이 음료만으로 이루어져있는 경우 예외를 던지는지 확인")
    @Test
    void onlyDrinkTest() {
        Order redWineOrder = Order.of(Menu.RED_WINE, 1);
        Order zeroColaOrder = Order.of(Menu.ZERO_COLA, 1);
        assertThrows(IllegalArgumentException.class, () -> Orders.of(List.of(redWineOrder, zeroColaOrder)));
    }

    @DisplayName("주문 메뉴 개수가 20개를 넘을 경우 예외를 던지는지 확인")
    @Test
    void exceedMaxMenuCountTest() {
        Order order = Order.of(Menu.SEAFOOD_PASTA, 100);
        assertThrows(IllegalArgumentException.class, () -> Orders.of(List.of(order)));
    }

    @DisplayName("총 주문 금액 계산이 올바른지 확인")
    @Test
    void totalOrderCostTest() {
        Order seafoodPastaOrder = Order.of(Menu.SEAFOOD_PASTA, 10);
        Order zeroColaOrder = Order.of(Menu.ZERO_COLA, 5);
        Orders orders = Orders.of(List.of(seafoodPastaOrder, zeroColaOrder));
        assertEquals(350000 + 15000, orders.calculateTotalOrderCost());
    }
}