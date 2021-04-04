package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ShowUnpopularBooks extends AbstractAction implements IAction {
    private static ShowUnpopularBooks instance;

    private ShowUnpopularBooks() {
    }

    public static ShowUnpopularBooks getInstance() {
        return Objects.requireNonNullElse(instance, new ShowUnpopularBooks());
    }

    @Override
    public void execute() {
        try {
            facade.showUnpopularBooks();
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }
}
