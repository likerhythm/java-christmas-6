package christmas.model;

public enum ErrorMessage {

    PREFIX              ("[ERROR] "),
    INVALID_ORDER_INPUT ("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DISCOUNT    ("혜택 금액이 유효하지 않습니다."),
    INVALID_VISIT_DATE  ("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
