package christmas.view.output;

import christmas.dto.DiscountDetailsDto;
import christmas.model.order.OrderDetailDto;

import java.util.List;
import java.util.Map;

import static christmas.view.output.OutputMessage.*;

public class OutputView {

    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void displayAskVisitDate() {
        System.out.println(ASK_VISIT_DATE);
    }

    public void displayAskOrder() {
        System.out.println(ASK_ORDER);
    }

    public void displayNoticeEventPreview(String visitDay) {
        System.out.println(NOTICE_EVENT_PREVIEW);
    }

    public void displayOrderDetails(List<OrderDetailDto> orderDetailDtos) {
        System.out.println(ORDER_DETAILS_HEADER);
        orderDetailDtos.forEach(dto -> {
                    System.out.println(buildOrderDetailMessage(dto));
                });
    }

    public void displayTotalOrderCostBeforeDiscount(int totalOrderCostBeforeDiscount) {
        System.out.println(TOTAL_ORDER_COST_BEFORE_DISCOUNT_HEADER);
        System.out.println(buildTotalOrderCostBeforeDiscountMessage(totalOrderCostBeforeDiscount));
    }

    public void displayGiveaway(Map<String, String> giveaway) {
        System.out.println(GIVEAWAY_HEADER);
        if (giveaway.isEmpty()) {
            System.out.println(NO_VALUE);
            return;
        }
        giveaway.forEach((key, value) -> System.out.println(buildGiveawayMessage(key, value)));
    }

    public void displayDiscountDetails(DiscountDetailsDto discountDetailsDto) {
        System.out.println(DISCOUNT_DETAILS_HEADER);
        if (discountDetailsDto.hasNoDiscount()) {
            System.out.println(NO_VALUE);
            return;
        }
        List<Integer> discountDetails = discountDetailsDto.getDiscountDetails();
        System.out.println(buildDiscountDetailsMessage(discountDetails));
    }

    public void displayTotalDiscount(int totalDiscount) {
        System.out.println(TOTAL_DISCOUNT_HEADER);
        System.out.println(buildTotalDiscountMessage(totalDiscount));
    }

    public void displayOrderCostAfterDiscount(int orderCostAfterDiscount) {
        System.out.println(ORDER_COST_AFTER_DISCOUNT_HEADER);
        System.out.println(buildOrderCostAfterDiscountMessage(orderCostAfterDiscount));
    }

    public void displayEventBadgeName(String badgeName) {
        System.out.println(EVENT_BADGE_NAME_HEADER);
        System.out.println(badgeName);
    }
}
