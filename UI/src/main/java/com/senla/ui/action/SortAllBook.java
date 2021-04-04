package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class SortAllBook extends AbstractAction implements IAction {
    private static SortAllBook instance;
    int type;

    private SortAllBook(int type) {
        this.type = type;
    }

    public static SortAllBook getInstance(int type) {
        return Objects.requireNonNullElse(instance, new SortAllBook(type));
    }

    @Override
    public void execute() {
        try {
            facade.sortAllBook(type);
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }
}
