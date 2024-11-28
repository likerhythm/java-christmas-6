package christmas.service;

import christmas.model.menu.Menu;
import christmas.repository.MenuRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuMapperService {

    private final MenuRepository menuRepository;

    public MenuMapperService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Map<Menu, Integer> mapParsedOrderInput(Map<String, String> parsedOrderInput) {
        return parsedOrderInput.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> menuRepository.findByName(entry.getKey()),
                        entry -> Integer.parseInt(entry.getValue()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}
