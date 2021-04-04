package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class CreateOrder extends AbstractAction implements IAction {
    private static CreateOrder instance;

    private CreateOrder() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new CreateOrder());
    }

    @Override
    public void execute() {
        long idBook = inputId();
        int quantity = inputQuantity();
        facade.showBooks();
        try {
            facade.createOrder(idBook, quantity);
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }

    private int inputQuantity() {
        System.out.print("Input quantity >> ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        return sc.nextInt();
    }

    private long inputId() {
        System.out.print("Choose a book for order >> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        return sc.nextLong();
    }

}
