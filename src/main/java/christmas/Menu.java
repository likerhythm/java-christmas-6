package christmas;

public enum Menu {

    MUSHROOM_SOUP("양송이스프"),
    TAPAS("타파스"),
    CAESAR_SALAD("시저샐러드"),
    T_BONE_STEAK("티본스테이크"),
    BARBECUE_RIBS("바비큐립"),
    SEAFOOD_PASTA("해산물파스타"),
    CHRISTMAS_PASTA("크리스마스파스타"),
    CHOCOLATE_CAKE("초코케이크"),
    ICE_CREAM("아이스크림"),
    ZERO_COLA("제로콜라"),
    RED_WINE("레드와인"),
    CHAMPAGNE("샴페인"),
    ;

    private final String name;

    Menu(String name) {
        this.name = name;
    }

    public boolean isEqualName(String menuName) {
        return this.name.equals(menuName);
    }
}
