package com.senla.ui.action;

import com.senla.store.Facade;

import java.util.Objects;

public class SerializableDB implements IAction {
    private static SerializableDB instance;

    private SerializableDB() {
    }

    public static SerializableDB getInstance() {
        return Objects.requireNonNullElse(instance, new SerializableDB());
    }

    @Override
    public void execute() {
        Facade.getInstance().serializableDao();
    }
}
