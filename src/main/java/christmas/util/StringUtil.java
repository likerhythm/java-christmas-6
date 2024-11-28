package christmas.util;

import java.text.NumberFormat;

public class StringUtil {

    public static String numberFormat(int number) {
        return NumberFormat.getInstance().format(number);
    }
}
