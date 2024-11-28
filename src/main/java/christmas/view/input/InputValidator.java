package christmas.view.input;

import christmas.model.ErrorMessage;

import java.util.Arrays;

public class InputValidator {

    public static final String ORDER_INPUT_DELIMITER = ",";
    public static final String ORDER_INPUT_REGEX = "^([가-힣a-zA-Z0-9\\s]+)-(\\s*[\\d]+\\s*)$";

    private static final int LOWER_LIMIT_OF_VISIT_DATE = 1;
    private static final int UPPER_LIMIT_OF_VISIT_DATE = 31;

    public String validateOrderInput(String orderInput) {
        String[] splitOrderInput = orderInput.split(ORDER_INPUT_DELIMITER);
        boolean isValid = Arrays.stream(splitOrderInput)
                .allMatch(split -> split.matches(ORDER_INPUT_REGEX));
        if (!isValid) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
        }
        return orderInput.trim();
    }

    public String validateVisitDate(String visitDate) {
        try {
            int integerVisitDate = validateIntegerVisitDate(visitDate);
            if (!(LOWER_LIMIT_OF_VISIT_DATE <= integerVisitDate && integerVisitDate <= UPPER_LIMIT_OF_VISIT_DATE)) {
                throw new IllegalArgumentException();
            }
            return String.valueOf(integerVisitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

    private int validateIntegerVisitDate(String visitDate) {
        String trimmedInput = visitDate.trim();
        return Integer.parseInt(trimmedInput);
    }
}
