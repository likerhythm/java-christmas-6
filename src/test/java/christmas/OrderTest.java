package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @DisplayName("메뉴 개수 검증")
    @Test
    void menuQuantityTest() {
        assertThrows(IllegalArgumentException.class, () -> Order.of(Menu.SEAFOOD_PASTA, 0));
    }
}
