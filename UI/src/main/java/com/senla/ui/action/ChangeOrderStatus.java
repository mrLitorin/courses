package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ChangeOrderStatus extends AbstractAction implements IAction {
    private static ChangeOrderStatus instance;

    private ChangeOrderStatus() {
    }

    public static ChangeOrderStatus getInstance() {
        return Objects.requireNonNullElse(instance, new ChangeOrderStatus());
    }

    @Override
    public void execute() {
        try {
            facade.changeOrderStatus(inputID(), inputType());
        } catch (UIException e) {
            LOGGER.error(e);
        }
    }

    private long inputID() {
        System.out.print("select order (ID) >>> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.println("Invalid input. Retry >> ");
        }
        return sc.nextLong();
    }

    private int inputType() {
        int type;
        System.out.println("1 - HOT");
        System.out.println("2 - COMPLETED");
        System.out.println("3 - CANCELED");
        System.out.print("Select order state (1 - 3) >> ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Retry (1 - 3)>> ");
            sc.nextLine();
        }
        type = sc.nextInt();
        if (type < 1 || type > 3) {
            inputType();
        }
        return type;
    }
}
