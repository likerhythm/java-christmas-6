package christmas.repository;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MenuRepositoryTest {

    MenuRepository menuRepository = new MenuRepository();

    @DisplayName("메뉴를 이름으로 찾기")
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
