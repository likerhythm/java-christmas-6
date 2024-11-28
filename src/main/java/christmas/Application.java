package christmas;

import christmas.model.event.EventService;
import christmas.repository.EventBadgeRepository;
import christmas.repository.MenuRepository;
import christmas.service.MenuMapperService;
import christmas.view.input.InputValidator;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {

        OrdersController ordersController = createOrdersController();

        ordersController.run();
    }

    private static OrdersController createOrdersController() {
        InputValidator inputValidator = new InputValidator();
        InputView inputView = new InputView(inputValidator);
        OutputView outputView = new OutputView();
        MenuRepository menuRepository = new MenuRepository();
        MenuMapperService menuMapperService = new MenuMapperService(menuRepository);
        EventService eventService = new EventService();
        EventBadgeRepository eventBadgeRepository = new EventBadgeRepository();
        OrdersController ordersController = new OrdersController(
                inputView,
                outputView,
                menuMapperService,
                eventService,
                eventBadgeRepository);
        return ordersController;
    }
}
