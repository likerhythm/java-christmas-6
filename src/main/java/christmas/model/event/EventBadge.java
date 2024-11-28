package christmas.model.event;

public enum EventBadge {

    NONE  ("없음", 0, 4999),
    STAR  ("별", 5000, 9999),
    TREE  ("트리", 10000, 19999),
    SANTA ("산타", 20000, Integer.MAX_VALUE),
    ;

    private final String name;
    private final int lowerLimitDiscountCost;
    private final int upperLimitDiscountCost;

    EventBadge(String name, int lowerLimitDiscountCost, int upperLimitDiscountCost) {
        this.name = name;
        this.lowerLimitDiscountCost = lowerLimitDiscountCost;
        this.upperLimitDiscountCost = upperLimitDiscountCost;
    }

    public boolean compareCondition(int totalDiscountCost) {
        return lowerLimitDiscountCost <= totalDiscountCost && totalDiscountCost <= upperLimitDiscountCost;
    }

    public String getName() {
        return name;
    }
}
