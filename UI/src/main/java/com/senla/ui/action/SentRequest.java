package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class SentRequest extends AbstractAction implements IAction {
    private static SentRequest instance;

    private SentRequest() {
    }

    public static SentRequest getInstance() {
        return Objects.requireNonNullElse(instance, new SentRequest());
    }

    @Override
    public void execute() {
        long id = inputID();
        int quantity = inputQuantity();
        try {
            facade.createRequest(id, quantity);
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }

    private long inputID() {
        facade.showBooks();
        System.out.print("select book for request (ID) >>> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.println("Invalid input. Retry >> ");
        }
        return sc.nextLong();
    }

    private int inputQuantity() {
        System.out.print("Input quantity >>> ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Invalid input. Retry >> ");
        }
        return sc.nextInt();
    }
}
