package by.bookstore.ui.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AmountOfIncome extends AbstractAction implements IAction {
    private static AmountOfIncome instance;

    private AmountOfIncome() {
    }

    public static AmountOfIncome getInstance() {
        return Objects.requireNonNullElse(instance, new AmountOfIncome());
    }

    @Override
    public void execute() {
        LocalDateTime time = inputDate();
        if (time != null) {
            facade.amountOfIncome(time);
        } else {
            System.out.println("Invalid date format.");
        }
    }

    private LocalDateTime inputDate() {
        LocalDateTime timeFrom;
        //todo: Упростить ввод даты
        System.out.print("Enter date from (EXAMPLE: ");
        System.out.print(LocalDateTime.now() + ") >> ");
        String time = sc.nextLine();
        try {
            timeFrom = LocalDateTime.parse(time);
            return timeFrom;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
