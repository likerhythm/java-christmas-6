package christmas.view.output;

import christmas.model.order.OrderDetailDto;
import christmas.util.StringUtil;

import java.util.List;

public class OutputMessage {

    public static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String ASK_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String ASK_ORDER = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String NOTICE_EVENT_PREVIEW = "12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String ORDER_DETAILS_HEADER = "<주문 메뉴>";
    public static final String TOTAL_ORDER_COST_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    public static final String GIVEAWAY_HEADER = "<증정 메뉴>";
    public static final String NO_VALUE = "없음";
    public static final String DISCOUNT_DETAILS_HEADER = "<혜택 내역>";
    public static final String TOTAL_DISCOUNT_HEADER = "<총혜택 금액>";
    public static final String ORDER_COST_AFTER_DISCOUNT_HEADER = "<할인 후 예상 결제 금액>";
    public static final String EVENT_BADGE_NAME_HEADER = "<12월 이벤트 배지>";

    private static final String COST = "%s원";
    private static final String ORDER_DETAIL = "%s %s개";
    private static final String TOTAL_ORDER_COST_BEFORE_DISCOUNT = COST;
    private static final String GIVEAWAY_DETAIL = "%s %s개";
    private static final List<String> DISCOUNT_DETAILS = List.of(
            "크리스마스 디데이 할인: " + COST + "\n",
            "평일 할인: " + COST + "\n",
            "주말 할인: " + COST + "\n",
            "특별 할인: " + COST + "\n",
            "증정 이벤트: " + COST + "\n"
    );
    private static final String TOTAL_DISCOUNT = COST;
    private static final String ORDER_COST_AFTER_DISCOUNT = COST;

    public static String buildOrderDetailMessage(OrderDetailDto dto) {
        return String.format(ORDER_DETAIL, dto.getMenuName(), dto.getQuantity());
    }

    public static String buildTotalOrderCostBeforeDiscountMessage(int totalOrderCostBeforeDiscount) {
        return String.format(TOTAL_ORDER_COST_BEFORE_DISCOUNT, StringUtil.numberFormat(totalOrderCostBeforeDiscount));
    }

    public static String buildGiveawayMessage(String menuName, String quantity) {
        return String.format(GIVEAWAY_DETAIL, menuName, quantity);
    }

    public static String buildDiscountDetailsMessage(List<Integer> discountDetails) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < DISCOUNT_DETAILS.size(); i++) {
            int discount = discountDetails.get(i);
            if (discount > 0) {
                String messageFormat = DISCOUNT_DETAILS.get(i);
                sb.append(String.format(messageFormat, StringUtil.numberFormat(-discount)));
            }
        }
        return sb.toString();
    }

    public static String buildTotalDiscountMessage(int totalDiscount) {
        totalDiscount = -totalDiscount;
        return String.format(TOTAL_DISCOUNT, totalDiscount);
    }

    public static String buildOrderCostAfterDiscountMessage(int orderCostAfterDiscount) {
        return String.format(ORDER_COST_AFTER_DISCOUNT, orderCostAfterDiscount);
    }
}
