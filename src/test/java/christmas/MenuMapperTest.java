package christmas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MenuMapperTest {

    MenuMapper menuMapper = new MenuMapper(new MenuRepository());

    @DisplayName("문자열을 Menu로 매핑하지 못할 경우 테스트에 실패합니다")
    @ParameterizedTest
    @MethodSource("provideParsedOrderInputAndMenuMapperResult")
    void mapToMenuTest(Map<String, String> parsedOrderInput, Map<Menu, Integer> mapToMenu) {
        assertEquals(mapToMenu, menuMapper.mapToMenu(parsedOrderInput));
    }

    static Stream<Arguments> provideParsedOrderInputAndMenuMapperResult() {
        return Stream.of(
                Arguments.of(Map.of("해산물파스타", "2"), Map.of(Menu.SEAFOOD_PASTA, 2)),
                Arguments.of(Map.of("초코케이크", "10"), Map.of(Menu.CHOCOLATE_CAKE, 10))
        );
    }

    @DisplayName("존재하지 않는 메뉴를 검증하지 못할 때 테스트에 실패합니다")
    @ParameterizedTest
    @MethodSource("provideInvalidParsedOrderInputAndMenuMapperResult")
    void errorMapToMenuTest(Map<String, String> parsedOrderInput) {
        assertThrows(IllegalArgumentException.class, () -> menuMapper.mapToMenu(parsedOrderInput));
    }

    static Stream<Arguments> provideInvalidParsedOrderInputAndMenuMapperResult() {
        return Stream.of(
                Arguments.of(Map.of("해산물 파스타", "2")),
                Arguments.of(Map.of("초코", "10"))
        );
    }
}
