package christmas;

import christmas.model.DecemberEvent;
import christmas.model.EventBadge;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.OrderDetailDto;
import christmas.model.order.Orders;
import christmas.repository.EventBadgeRepository;
import christmas.service.MenuMapperService;
import christmas.util.StringParser;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdersController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MenuMapperService menuMapperService;
    private final DecemberEvent decemberEvent;
    private final EventBadgeRepository eventBadgeRepository;

    public OrdersController(InputView inputView,
                            OutputView outputView,
                            MenuMapperService menuMapperService,
                            DecemberEvent decemberEvent,
                            EventBadgeRepository eventBadgeRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuMapperService = menuMapperService;
        this.decemberEvent = decemberEvent;
        this.eventBadgeRepository = eventBadgeRepository;
    }

    public void run() {
        try {
            outputView.displayWelcomeMessage();
            String visitDay = getVisitDate();

            Orders orders = getOrders();

            List<OrderDetailDto> orderDetailDtos = orders.createOrderDetailDtos(); // 주문 메뉴

            int totalOrderCost = orders.calculateTotalOrderCost(); // 할인 전 총 주문 금액
            decemberEvent.apply(totalOrderCost);

            Map<String, String> giveaway = decemberEvent.getGiveaway(totalOrderCost); // 증정 메뉴

            LocalDate visitDate = LocalDate.of(2024, 12, Integer.parseInt(visitDay));
            int christmasDdayDiscount = decemberEvent.calculateChristmasDdayDiscount(visitDate);
            int weekdayDiscount = decemberEvent.calculateWeekdayDiscount(visitDate, orders.countDessert());
            int weekendDiscount = decemberEvent.calculateWeekendDiscount(visitDate, orders.countMainMenu());
            int specialDiscount = decemberEvent.calculateSpecialDiscount(visitDate);
            int giveawayDiscount = decemberEvent.calculateGiveawayDiscount(totalOrderCost);
            DiscountDetailsDto discountDetailsDto = new DiscountDetailsDto();
            discountDetailsDto.addDetail(christmasDdayDiscount);
            discountDetailsDto.addDetail(weekdayDiscount);
            discountDetailsDto.addDetail(weekendDiscount);
            discountDetailsDto.addDetail(specialDiscount);
            discountDetailsDto.addDetail(giveawayDiscount);

            int totalDiscount = christmasDdayDiscount // 총 혜택 금액
                    + weekdayDiscount
                    + weekendDiscount
                    + specialDiscount
                    + giveawayDiscount;

            int orderCostAfterDiscount = totalOrderCost - totalDiscount; // 할인 후 예상 결제 금액
            String badgeName = eventBadgeRepository.getBadgeName(totalDiscount); // 배지 이름

            outputView.displayNoticeEventPreviewComment(visitDay);
            outputView.displayOrderDetails(orderDetailDtos);
            outputView.displayTotalOrderCostBeforeDiscount(totalOrderCost);
            outputView.displayGiveaway(giveaway);
            outputView.displayDiscountDetails(discountDetailsDto);
            outputView.displayTotalDiscount(totalDiscount);
            outputView.displayOrderCostAfterDiscount(orderCostAfterDiscount);
            outputView.displayEventBadgeName(badgeName);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private Orders getOrders() {
        String orderInput = getOrderInput();
        Map<String, String> parsedOrderInput = StringParser.parseOrderInput(orderInput);
        Map<Menu, Integer> menuIntegerMap = menuMapperService.mapParsedOrderInput(parsedOrderInput);
        List<Order> rawOrders = menuIntegerMap.entrySet().stream()
                .map(entry -> Order.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return Orders.of(rawOrders);
    }

    private String getVisitDate() {
        outputView.displayAskVisitDate();
        return inputView.getVisitDate();
    }

    private String getOrderInput() {
        outputView.displayAskOrder();
        return inputView.getOrderInput();
    }
}
