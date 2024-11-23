package christmas;

public class InputValidator {

    private static final String ORDER_INPUT_UNIT_REGEX = "[가-힣a-zA-Z0-9\\s]+-\\s*[\\d]+";;
    private static final String ORDER_INPUT_REGEX = ORDER_INPUT_UNIT_REGEX +"(," + ORDER_INPUT_UNIT_REGEX + ")*";

    public String validateVisitDate(String visitDate) {
        try {
            int integerVisitDate = validateIntegerVisitDate(visitDate);
            if (!(1 <= integerVisitDate && integerVisitDate <= 31)) {
                throw new IllegalArgumentException();
            }
            return String.valueOf(integerVisitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private int validateIntegerVisitDate(String visitDate) {
        String trimmedInput = visitDate.trim();
        return Integer.parseInt(trimmedInput);
    }

    public String validateOrderInput(String orderInput) {
        String trimmedOrderInput = orderInput.trim();
        if (!trimmedOrderInput.matches(ORDER_INPUT_REGEX)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return trimmedOrderInput;
    }
}
