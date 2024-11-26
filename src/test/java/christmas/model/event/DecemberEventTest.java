package christmas.model.event;

import christmas.model.DecemberEvent;
import christmas.model.menu.Menu;
import christmas.model.order.Orders;
import christmas.model.order.Order;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DecemberEventTest {

    static DecemberEvent decemberEvent;

    @BeforeAll
    static void beforeAll() {

    }

    @DisplayName("크리스마스 디데이 할인 금액이 올바르게 계산되는지 확인")
    @Test
    void christmasDdayDiscountTest() {
        Orders orders = Orders.of(List.of(Order.of(Menu.CHOCOLATE_CAKE, 1), Order.of(Menu.SEAFOOD_PASTA, 2)));
        decemberEvent = new DecemberEvent();
        decemberEvent.apply(orders.calculateTotalOrderCost());
        LocalDate date = LocalDate.of(2024, 12, 15);
        Assertions.assertEquals(2400, decemberEvent.calculateChristmasDdayDiscount(date));
    }

    @DisplayName("평일 할인 금액이 올바르게 계산되는지 확인")
    @Test
    void weekdayDiscountTest() {
        Orders orders = Orders.of(List.of(Order.of(Menu.CHOCOLATE_CAKE, 1), Order.of(Menu.SEAFOOD_PASTA, 2)));
        decemberEvent = new DecemberEvent();
        decemberEvent.apply(orders.calculateTotalOrderCost());
        LocalDate date = LocalDate.of(2024, 12, 14);
        Assertions.assertEquals(2023, decemberEvent.calculateWeekdayDiscount(date, orders.countDessert()));
    }

    @DisplayName("주말 할인 금액이 올바르게 계산되는지 확인")
    @Test
    void weekendDiscountTest() {
        Orders orders = Orders.of(List.of(Order.of(Menu.CHOCOLATE_CAKE, 1), Order.of(Menu.SEAFOOD_PASTA, 2)));
        decemberEvent = new DecemberEvent();
        decemberEvent.apply(orders.calculateTotalOrderCost());
        LocalDate date = LocalDate.of(2024, 12, 15);
        Assertions.assertEquals(4046, decemberEvent.calculateWeekendDiscount(date, orders.countMainMenu()));
    }

    @DisplayName("특별 할인 금액이 올바르게 계산되는지 확인")
    @Test
    void specialDiscountTest() {
        Orders orders = Orders.of(List.of(Order.of(Menu.CHOCOLATE_CAKE, 1), Order.of(Menu.SEAFOOD_PASTA, 2)));
        decemberEvent = new DecemberEvent();
        decemberEvent.apply(orders.calculateTotalOrderCost());
        LocalDate date = LocalDate.of(2024, 12, 25);
        Assertions.assertEquals(1000, decemberEvent.calculateSpecialDiscount(date));
    }

    @DisplayName("증정 이벤트가 올바르게 수행되는지 확인")
    @Test
    void giveawayTest() {
        Orders orders = Orders.of(List.of(Order.of(Menu.CHOCOLATE_CAKE, 1), Order.of(Menu.SEAFOOD_PASTA, 10)));
        decemberEvent = new DecemberEvent();
        decemberEvent.apply(orders.calculateTotalOrderCost());
        Assertions.assertEquals(Map.of("샴페인", "1"), decemberEvent.getGiveaway(orders.calculateTotalOrderCost()));
    }

    @DisplayName("총 주문 금액에 따른 이벤트 적용 유무 확인")
    @Test
    void minCostToApplyEventTest() {
        Orders orders = Orders.of(List.of(Order.of(Menu.ZERO_COLA, 1), Order.of(Menu.ICE_CREAM, 1)));
        decemberEvent = new DecemberEvent();
        decemberEvent.apply(orders.calculateTotalOrderCost());
        LocalDate date = LocalDate.of(2024, 12, 15);
        int christmasDdayDiscount = decemberEvent.calculateChristmasDdayDiscount(date);
        int specialDiscount = decemberEvent.calculateSpecialDiscount(date);
        int weekdayDiscount = decemberEvent.calculateWeekdayDiscount(date, orders.countDessert());
        int weekendDiscount = decemberEvent.calculateWeekendDiscount(date, orders.countMainMenu());
        Map<String, String> giveaway = decemberEvent.getGiveaway(orders.calculateTotalOrderCost());

        int totalDiscount = christmasDdayDiscount + specialDiscount + weekdayDiscount + weekendDiscount;
        Assertions.assertEquals(0, totalDiscount);
        Assertions.assertEquals(Map.of(), giveaway);
    }
}
