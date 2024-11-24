package christmas.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    InputValidator inputValidator = new InputValidator();

    @DisplayName("올바른 방문 날짜 검증")
    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "31",
            "10",
            " 1",
            "31 "
    })
    void validVisitDateTest(String input) {
        assertDoesNotThrow(() -> inputValidator.validateVisitDate(input));
    }

    @DisplayName("올바르지 않은 방문 날짜 검증")
    @ParameterizedTest
    @ValueSource(strings = {
            "0",
            "-1",
            "1일",
            "32"
    })
    void invalidVisitDateTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validateVisitDate(input));
    }

    @DisplayName("올바른 주문 형식을 검증")
    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-2,레드와인-1,초코케이크-1",
            "해산물파스타-2, 레드와인-1, 초코케이크-1",
            "해산물파스타-2",
            "해산물 파스타-2 ",
            "해산물 파스타 - 2",
    })
    void validOrderInputFormatTest(String orderInput) {
        assertDoesNotThrow(() -> inputValidator.validateOrderInput(orderInput));
    }

    @DisplayName("올바르지 않은 주문 형식 검증")
    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-2,레드와인-1,초코케이크-",
            "해산물파스타",
            "해산물파스타2",
            "해산물파스타/2",
            "해산물파스타-해물파스타",
            " ",
            "\n",
    })
    void invalidOrderInputFormatTest(String orderInput) {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validateOrderInput(orderInput));
    }
}
