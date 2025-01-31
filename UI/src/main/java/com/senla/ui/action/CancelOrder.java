package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class CancelOrder extends AbstractAction implements IAction {
    private static CancelOrder instance;

    private CancelOrder() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new CancelOrder());
    }

    @Override
    public void execute() {
        long idOrder;
        facade.showOrders();
        System.out.print("Select order to cancel (ID)>> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        idOrder = sc.nextLong();
        try {
            facade.cancelOrder(idOrder);
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }
}
