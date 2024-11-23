package christmas;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MenuRepositoryTest {

    MenuRepository menuRepository = new MenuRepository();

    @DisplayName("이름을 메뉴를 찾지 못한 경우 테스트에 실패합니다")
    @ParameterizedTest
    @MethodSource("provideMenuNameAndExpectMenu")
    void findByNameTest(String menuName, Menu expectMenu) {
        Assertions.assertEquals(expectMenu, menuRepository.findByName(menuName));
    }

    static Stream<Arguments> provideMenuNameAndExpectMenu() {
        return Stream.of(
                Arguments.of("양송이스프", Menu.MUSHROOM_SOUP),
                Arguments.of("타파스", Menu.TAPAS),
                Arguments.of("초코케이크", Menu.CHOCOLATE_CAKE),
                Arguments.of("해산물파스타", Menu.SEAFOOD_PASTA)
        );
    }
}
