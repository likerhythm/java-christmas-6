package christmas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuMapper {

    private final MenuRepository menuRepository;

    public MenuMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Map<Menu, Integer> mapToMenu(Map<String, String> parsedOrderInput) {
        return parsedOrderInput.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> menuRepository.findByName(entry.getKey()),
                        entry -> Integer.parseInt(entry.getValue()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}
