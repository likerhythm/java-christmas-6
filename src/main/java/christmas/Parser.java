package christmas;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    public Map<String, String> parseOrderInput(String orderInput) {
        String[] splitOrderInput = orderInput.split(",");
        return Arrays.stream(splitOrderInput)
                .collect(Collectors.toMap(
                        splitUnit -> splitUnit.split("-")[0].trim(),
                        splitUnit -> splitUnit.split("-")[1].trim(),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}
