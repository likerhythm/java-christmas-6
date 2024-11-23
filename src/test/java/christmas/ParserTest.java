package christmas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

public class ParserTest {

    Parser parser = new Parser();

    @DisplayName("메뉴 이름과 주문 개수가 올바르게 파싱되지 않을 경우 테스트가 실패합니다")
    @ParameterizedTest
    @MethodSource("provideOrderInputAndExpectParseResult")
    void parseOrderInputTest(String orderInput, Map<String, String> expectResult) {
        Assertions.assertEquals(expectResult, parser.parseOrderInput(orderInput));
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
}
