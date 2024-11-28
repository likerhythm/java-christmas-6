package christmas;

import christmas.dto.DiscountDetailsDto;
import christmas.model.ErrorMessage;
import christmas.model.event.EventDates;
import christmas.service.EventService;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.OrderDetailDto;
import christmas.model.order.Orders;
import christmas.repository.EventBadgeRepository;
import christmas.service.MenuMapperService;
import christmas.util.RetryExecutor;
import christmas.util.StringParser;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdersController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MenuMapperService menuMapperService;
    private final EventService eventService;
    private final EventBadgeRepository eventBadgeRepository;

    public OrdersController(InputView inputView,
                            OutputView outputView,
                            MenuMapperService menuMapperService,
                            EventService eventService,
                            EventBadgeRepository eventBadgeRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuMapperService = menuMapperService;
        this.eventService = eventService;
        this.eventBadgeRepository = eventBadgeRepository;
    }

    public void run() {
        try {
            orderProcess();
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.PREFIX.getMessage() + e.getMessage());
        }
    }

    private void orderProcess() {
        outputView.displayWelcomeMessage();
        String visitDay = RetryExecutor.executeWithRetry(this::getVisitDate);
        Orders orders = RetryExecutor.executeWithRetry(this::getOrders);
        LocalDate visitDate = LocalDate.of(EventDates.EVENT_YEAR, EventDates.EVENT_MONTH, Integer.parseInt(visitDay));
        eventService.apply(orders, visitDate);
        runOutputView(visitDay, orders);
    }

    private String getVisitDate() {
        outputView.displayAskVisitDate();
        return inputView.getVisitDate();
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

    private String getOrderInput() {
        outputView.displayAskOrder();
        return inputView.getOrderInput();
    }

    private void runOutputView(String visitDay, Orders orders) {
        int totalOrderCost = displayBeforeDiscount(visitDay, orders);
        int totalDiscount = displayDiscount();
        displayAfterDiscount(totalOrderCost, totalDiscount);
    }

    private int displayBeforeDiscount(String visitDay, Orders orders) {
        outputView.displayNoticeEventPreview(visitDay);
        List<OrderDetailDto> orderDetailDtos = orders.createOrderDetailDtos(); // 주문 메뉴
        outputView.displayOrderDetails(orderDetailDtos);
        int totalOrderCost = orders.calculateTotalOrderCost(); // 할인 전 총 주문 금액
        outputView.displayTotalOrderCostBeforeDiscount(totalOrderCost);

        return totalOrderCost;
    }

    private int displayDiscount() {
        Map<String, String> giveaway = eventService.getGiveaway(); // 증정 메뉴
        outputView.displayGiveaway(giveaway);
        DiscountDetailsDto discountDetailsDto = eventService.createDiscountDetailsDto();
        outputView.displayDiscountDetails(discountDetailsDto);
        int totalDiscount = eventService.calculateTotalDiscount();
        outputView.displayTotalDiscount(totalDiscount);

        return totalDiscount;
    }

    private void displayAfterDiscount(int totalOrderCost, int totalDiscount) {
        int orderCostAfterDiscount = totalOrderCost - totalDiscount; // 할인 후 예상 결제 금액
        outputView.displayOrderCostAfterDiscount(orderCostAfterDiscount);
        String badgeName = eventBadgeRepository.getBadgeName(totalDiscount); // 배지 이름
        outputView.displayEventBadgeName(badgeName);
    }
}
