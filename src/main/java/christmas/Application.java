package christmas;

import christmas.model.DecemberEvent;
import christmas.repository.EventBadgeRepository;
import christmas.repository.MenuRepository;
import christmas.service.MenuMapperService;
import christmas.view.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        InputValidator inputValidator = new InputValidator();
        InputView inputView = new InputView(inputValidator);
        OutputView outputView = new OutputView();
        MenuRepository menuRepository = new MenuRepository();
        MenuMapperService menuMapperService = new MenuMapperService(menuRepository);
        DecemberEvent decemberEvent = new DecemberEvent();
        EventBadgeRepository eventBadgeRepository = new EventBadgeRepository();
        OrdersController ordersController = new OrdersController(
                inputView,
                outputView,
                menuMapperService,
                decemberEvent,
                eventBadgeRepository);

        ordersController.run();
    }
}
