package christmas.model.menu;

import static christmas.model.menu.MenuType.*;

public enum Menu {

    MUSHROOM_SOUP   ("양송이스프", APPETIZER, 6000),
    TAPAS           ("타파스", APPETIZER, 5500),
    CAESAR_SALAD    ("시저샐러드", APPETIZER, 8000),
    T_BONE_STEAK    ("티본스테이크", MAIN, 55000),
    BARBECUE_RIBS   ("바비큐립", MAIN, 54000),
    SEAFOOD_PASTA   ("해산물파스타", MAIN, 35000),
    CHRISTMAS_PASTA ("크리스마스파스타", MAIN, 25000),
    CHOCOLATE_CAKE  ("초코케이크", DESSERT, 15000),
    ICE_CREAM       ("아이스크림", DESSERT, 5000),
    ZERO_COLA       ("제로콜라", DRINK, 3000),
    RED_WINE        ("레드와인", DRINK, 60000),
    CHAMPAGNE       ("샴페인", DRINK, 25000),
    ;

    private final String name;
    private final MenuType type;
    private final int cost;

    Menu(String name, MenuType menuType, int cost) {
        this.name = name;
        this.type = menuType;
        this.cost = cost;
    }

    public boolean isEqualName(String menuName) {
        return this.name.equals(menuName);
    }

    public boolean isDrink() {
        return type == DRINK;
    }

    public int calculateCost(int quantity) {
        return cost * quantity;
    }

    public boolean isDessert() {
        return type == DESSERT;
    }

    public boolean isMain() {
        return type == MAIN;
    }

    public String getName() {
        return name;
    }
}
