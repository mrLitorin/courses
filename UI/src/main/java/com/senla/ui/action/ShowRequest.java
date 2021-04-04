package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ShowRequest extends AbstractAction implements IAction {
    private static ShowRequest instance;

    private ShowRequest() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowRequest());
    }

    @Override
    public void execute() {
        try {
            facade.showRequests();
        } catch (UIException e) {
            LOGGER.error(e);
        }

    }
}
