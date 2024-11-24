package christmas;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringParser {

    public Map<String, String> parseOrderInput(String orderInput) {
        String[] splitOrderInput = orderInput.split(InputView.ORDER_INPUT_DELIMITER);
        return Arrays.stream(splitOrderInput)
                .map(this::validateAndMatch)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> {
                            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        },
                        LinkedHashMap::new
                ));
    }

    private Map.Entry<String, String> validateAndMatch(String input) {
        Pattern pattern = Pattern.compile(InputValidator.ORDER_INPUT_REGEX);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return Map.entry(matcher.group(1).trim(), matcher.group(2).trim());
    }
}
