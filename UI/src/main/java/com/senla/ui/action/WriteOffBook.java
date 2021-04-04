package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class WriteOffBook extends AbstractAction implements IAction {
    private static WriteOffBook instance;

    private WriteOffBook() {
    }

    public static WriteOffBook getInstance() {
        return Objects.requireNonNullElse(instance, new WriteOffBook());
    }

    @Override
    public void execute() {
        long idBook = inputIdBook();
        try {
            facade.writeOff(idBook);
        } catch (UIException e) {
            LOGGER.error(e);
        }
    }


    private long inputIdBook() {
        facade.showBooks();
        System.out.print("Choose a book by ID or 0 for exit >> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid ID. ");
            System.out.print("Input ID book >> ");
        }
        return sc.nextLong();
    }
}
