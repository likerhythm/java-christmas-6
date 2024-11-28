package christmas.util;

import christmas.model.ErrorMessage;

import java.util.function.Supplier;

public class RetryExecutor {

    public static <T> T executeWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.PREFIX.getMessage() + e.getMessage());
            }
        }
    }
}
