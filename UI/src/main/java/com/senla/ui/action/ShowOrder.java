package com.senla.ui.action;

import com.senla.ui.exeption.UIException;

import java.util.Objects;

public class ShowOrder extends AbstractAction implements IAction {
    private static ShowOrder instance;

    private ShowOrder() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowOrder());
    }

    @Override
    public void execute() {
        try {
            facade.showOrders();
        } catch (UIException e) {
            LOGGER.error(e);
        }
    }
}
