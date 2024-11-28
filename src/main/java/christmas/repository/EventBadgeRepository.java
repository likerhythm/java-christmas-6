package christmas.repository;

import christmas.model.ErrorMessage;
import christmas.model.event.EventBadge;

import java.util.Arrays;

public class EventBadgeRepository {

    public String getBadgeName(int totalDiscountCost) {
        return Arrays.stream(EventBadge.values())
                .filter(badge -> badge.compareCondition(totalDiscountCost))
                .findFirst()
                .map(EventBadge::getName)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_DISCOUNT.getMessage()));
    }
}
