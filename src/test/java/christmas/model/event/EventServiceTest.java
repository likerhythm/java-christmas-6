package christmas.model.event;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;
import christmas.model.order.Order;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

public class EventServiceTest {

    EventService eventService = new EventService();

    @DisplayName("할인 내역이 올바른지 확인")
    @Test
    void discountDetailsTest() {
        Order seafoodPastaOrder = Order.of(Menu.SEAFOOD_PASTA, 2);
        Order redWineOrder = Order.of(Menu.RED_WINE, 1);
        Order iceCreamOrder = Order.of(Menu.ICE_CREAM, 1);
                List<Order> orderList = List.of(seafoodPastaOrder,
                redWineOrder,
                iceCreamOrder);

        Orders orders = Orders.of(orderList);

        LocalDate visitDate = LocalDate.of(2024, 12, 25);
        eventService.apply(orders, visitDate);
        Assertions.assertEquals(List.of(3400, 2023, 0, 1000, 25000), eventService.createDiscountDetailsDto().getDiscountDetails());
    }
}
