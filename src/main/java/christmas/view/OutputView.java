package christmas.view;

import christmas.DiscountDetailsDto;
import christmas.model.order.OrderDetailDto;
import christmas.util.StringUtil;

import java.util.List;
import java.util.Map;

public class OutputView {

    private final List<String> discountDetailsFormat = List.of(
            "크리스마스 디데이 할인: %s원",
            "평일 할인: %s원",
            "주말 할인: %s원",
            "특별 할인: %s원",
            "증정 이벤트: %s원"
    );

    public void displayWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void displayAskVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void displayAskOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void displayNoticeEventPreviewComment(String visitDay) {
        System.out.println("12월 " + visitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void displayOrderDetails(List<OrderDetailDto> orderDetailDtos) {
        System.out.println("<주문 메뉴>");
        orderDetailDtos
                .forEach(dto -> {
                    System.out.println(dto.getMenuName() + " " + dto.getQuantity() + "개");
                });
    }

    public void displayTotalOrderCostBeforeDiscount(int totalOrderCostBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(StringUtil.numberFormat(totalOrderCostBeforeDiscount) + "원");
    }

    public void displayGiveaway(Map<String, String> giveaway) {
        System.out.println("<증정 메뉴>");
        if (giveaway.isEmpty()) {
            System.out.println("없음");
            return;
        }
        giveaway.forEach((key, value) -> System.out.println(key + " " + value + "개"));
    }

    public void displayDiscountDetails(DiscountDetailsDto discountDetailsDto) {
        System.out.println("<혜택 내역>");
        if (discountDetailsDto.hasNoDiscount()) {
            System.out.println("없음");
            return;
        }
        List<Integer> discountDetails = discountDetailsDto.getDiscountDetails();
        printDiscountDetails(discountDetails);
    }

    public void displayTotalDiscount(int totalDiscount) {
        System.out.println("<총혜택 금액>");
        totalDiscount = -totalDiscount;
        System.out.println(StringUtil.numberFormat(totalDiscount) + "원");
    }

    public void displayOrderCostAfterDiscount(int orderCostAfterDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(StringUtil.numberFormat(orderCostAfterDiscount));
    }

    public void displayEventBadgeName(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

    private void printDiscountDetails(List<Integer> discountDetails) {
        for(int i = 0; i < discountDetailsFormat.size(); i++) {
            int discount = discountDetails.get(i);
            if (discount > 0) {
                String format = discountDetailsFormat.get(i);
                System.out.println(String.format(format, StringUtil.numberFormat(-discount)));
            }
        }
    }
}
