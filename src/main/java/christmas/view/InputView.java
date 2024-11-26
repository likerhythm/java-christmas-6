package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static final String ORDER_INPUT_DELIMITER = ",";

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String getVisitDate() {
        return inputValidator.validateVisitDate(readLine());
    }

    public String getOrderInput() {
        return inputValidator.validateOrderInput(readLine());
    }

    private String readLine() {
        return Console.readLine();
    }
}
