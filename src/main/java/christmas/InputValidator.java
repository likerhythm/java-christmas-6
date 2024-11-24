package christmas;

import java.util.Arrays;

public class InputValidator {

    public static final String ORDER_INPUT_REGEX = "^([가-힣a-zA-Z0-9\\s]+)-(\\s*[\\d]+\\s*)$";

    public String validateOrderInput(String orderInput) {
        String[] splitOrderInput = orderInput.split(InputView.ORDER_INPUT_DELIMITER);
        boolean isValid = Arrays.stream(splitOrderInput)
                .allMatch(split -> split.matches(ORDER_INPUT_REGEX));
        if (!isValid) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return orderInput.trim();
    }

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
}
