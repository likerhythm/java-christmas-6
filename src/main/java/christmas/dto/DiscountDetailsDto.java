package christmas.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 다음 순서로 add해야 한다.
 * 크리스마스 디데이 할인
 * 평일 할인
 * 주말 할인
 * 특별 할인
 * 증정 이벤트 할인
 */
public class DiscountDetailsDto {

    private final List<Integer> discountDetails = new ArrayList<>();

    public void addDetail(int discount) {
        discountDetails.add(discount);
    }

    public List<Integer> getDiscountDetails() {
        return discountDetails;
    }

    public boolean hasNoDiscount() {
        return discountDetails.stream()
                .allMatch(discount -> discount == 0);
    }
}
