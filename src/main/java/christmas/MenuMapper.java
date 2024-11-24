package christmas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MenuMapper {

    private final MenuRepository menuRepository;

    public MenuMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Map<Menu, Integer> mapParsedOrderInput(Map<String, String> parsedOrderInput) {
        LinkedHashMap<Menu, Integer> menuAndQuantity = createMap(parsedOrderInput);
        boolean isAllValidQuantity = menuAndQuantity.values().stream()
                .allMatch(v -> v > 0);
        if (!isAllValidQuantity) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return menuAndQuantity;
    }

    private LinkedHashMap<Menu, Integer> createMap(Map<String, String> parsedOrderInput) {
        return parsedOrderInput.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> menuRepository.findByName(entry.getKey()),
                        entry -> Integer.parseInt(entry.getValue()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}
