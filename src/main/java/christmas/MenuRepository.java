package christmas;

import java.util.Arrays;

public class MenuRepository {

    public Menu findByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isEqualName(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }
}
