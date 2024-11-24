package christmas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Stream;

public class StringParserTest {

    StringParser stringParser = new StringParser();

    @DisplayName("메뉴 이름과 주문 개수 파싱")
    @ParameterizedTest
    @MethodSource("provideOrderInputAndExpectParseResult")
    void parseOrderInputTest(String orderInput, Map<String, String> expectResult) {
        Assertions.assertEquals(expectResult, stringParser.parseOrderInput(orderInput));
    }

    static Stream<Arguments> provideOrderInputAndExpectParseResult() {
        return Stream.of(
                Arguments.of(
                        "해산물파스타-2,레드와인-1,초코케이크-1",
                        Map.of("해산물파스타", "2", "레드와인", "1", "초코케이크", "1")
                ),
                Arguments.of(
                        "해산물파스타 - 2",
                        Map.of("해산물파스타", "2")
                ),
                Arguments.of(
                        "해산물파스타 - 2, 레드와인-1",
                        Map.of("해산물파스타", "2", "레드와인", "1")
                )
        );
    }

    @DisplayName("중복된 주문을 검증")
    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-2,해산물파스타-3",
            "초코케이크-2,초코케이크-3"
    })
    void duplicatedOrderInputTest(String orderInput) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> stringParser.parseOrderInput(orderInput));
    }
}
