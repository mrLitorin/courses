package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ShowBookDescription extends AbstractAction implements IAction {
    private static ShowBookDescription instance;

    private ShowBookDescription() {
    }

    public static ShowBookDescription getInstance() {
        return Objects.requireNonNullElse(instance, new ShowBookDescription());
    }

    @Override
    public void execute() {
        try {
            long idBook = inputIdBook();
            facade.showBookDescription(idBook);
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
