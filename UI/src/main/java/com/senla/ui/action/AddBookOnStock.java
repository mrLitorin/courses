package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class AddBookOnStock extends AbstractAction implements IAction {
    private static AddBookOnStock instance;

    private AddBookOnStock() {
    }

    public static AddBookOnStock getInstance() {
        return Objects.requireNonNullElse(instance, new AddBookOnStock());
    }

    @Override
    public void execute() {
        facade.ShowBookRequests();
        System.out.print("Input ID book >> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid num. Retry >> ");
        }
        long idBook = sc.nextLong();
        LOGGER.info("Adding a book to the store.");
        try {
            facade.addBookOnStock(idBook);
        } catch (UIException e) {
            LOGGER.error("Book not added.", e);
        }
    }
}
