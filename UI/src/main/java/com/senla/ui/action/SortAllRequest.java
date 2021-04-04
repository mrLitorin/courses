package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class SortAllRequest extends AbstractAction implements IAction {
    private static SortAllRequest instance;

    private SortAllRequest() {
    }

    public static SortAllRequest getInstance() {
        return Objects.requireNonNullElse(instance, new SortAllRequest());
    }

    @Override
    public void execute() {
        try {
            facade.sortAllRequest(inputSortBy());
        } catch (UIException e) {
            LOGGER.error(e);
        }
    }

    private int inputSortBy() {
        int type;
        System.out.println("1 - title");
        System.out.println("2 - quantity");
        System.out.print("Select type (1 - 2) >> ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Retry (1 - 2) >> ");
            sc.nextLine();
        }
        type = sc.nextInt();
        if (type < 1 || type > 2) {
            inputSortBy();
        }
        return type;
    }
}
