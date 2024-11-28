package christmas.model.order;

public record OrderDetailDto(String menuName, String quantity) {

    public String getMenuName() {
        return menuName;
    }

    public String getQuantity() {
        return quantity;
    }
}
