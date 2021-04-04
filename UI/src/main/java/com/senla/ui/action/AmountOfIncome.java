package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

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
        try {
            LocalDateTime time = inputDate();
            facade.amountOfIncome(time);
        } catch (UIException e) {
            LOGGER.error(e);
        }
    }

    private LocalDateTime inputDate() {
        LocalDateTime timeFrom;
        //todo: Упростить ввод даты
        System.out.print("Enter date from (EXAMPLE: ");
        System.out.print(LocalDateTime.now() + ") >> ");
        try {
            String time = sc.nextLine();
            timeFrom = LocalDateTime.parse(time);
            return timeFrom;
        } catch (DateTimeParseException e) {
            LOGGER.error("Invalid date.");
            throw new UIException("Bad arguments.", e);
        }
    }
}
