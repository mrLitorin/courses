package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ShowBooks extends AbstractAction implements IAction {
    private static ShowBooks instance;

    private ShowBooks() {
    }

    public static ShowBooks getInstance() {
        return Objects.requireNonNullElse(instance, new ShowBooks());
    }

    @Override
    public void execute() {
        try {
            facade.showBooks();
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }
}
