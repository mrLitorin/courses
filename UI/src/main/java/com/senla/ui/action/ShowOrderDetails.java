package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ShowOrderDetails extends AbstractAction implements IAction {
    private static ShowOrderDetails instance;

    private ShowOrderDetails() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowOrderDetails());
    }

    @Override
    public void execute() {
        long idOrder = inputId();
        try {
            facade.showOrderDetails(idOrder);
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }

    private long inputId() {
        facade.showOrders();
        System.out.print("Select order to cancel (ID)>> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        return sc.nextLong();
    }
}
