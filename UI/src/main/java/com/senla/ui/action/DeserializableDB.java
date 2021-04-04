package com.senla.ui.action;

import com.senla.store.Facade;

import java.util.Objects;

public class DeserializableDB implements IAction {
    private static DeserializableDB instance;

    private DeserializableDB() {
    }

    public static DeserializableDB getInstance() {
        return Objects.requireNonNullElse(instance, new DeserializableDB());
    }

    @Override
    public void execute() {
        Facade.getInstance().deserializableDao();
    }
}
