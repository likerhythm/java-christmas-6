package christmas.repository;

import christmas.model.ErrorMessage;
import christmas.model.menu.Menu;

import java.util.Arrays;

public class MenuRepository {

    public Menu findByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isEqualName(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage()));
    }
}
