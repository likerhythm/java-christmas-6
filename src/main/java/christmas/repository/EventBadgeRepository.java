package christmas.repository;

import christmas.model.EventBadge;

import java.util.Arrays;

public class EventBadgeRepository {

    public String getBadgeName(int totalDiscountCost) {
        return Arrays.stream(EventBadge.values())
                .filter(badge -> badge.compareCondition(totalDiscountCost))
                .findFirst()
                .map(EventBadge::getName)
                .orElseThrow(() -> new IllegalArgumentException("혜택 금액이 유효하지 않습니다"));
    }
}
