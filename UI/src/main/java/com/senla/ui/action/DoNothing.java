package com.senla.ui.action;

import java.util.Objects;

public class DoNothing extends AbstractAction implements IAction {
    private static DoNothing instance;

    private DoNothing() {
    }

    public static DoNothing getInstance() {
        return Objects.requireNonNullElse(instance, new DoNothing());
    }

    @Override
    public void execute() {

    }
}
