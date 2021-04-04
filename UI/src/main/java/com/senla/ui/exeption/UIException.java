package com.senla.ui.exeption;

public class UIException extends RuntimeException {
    public UIException() {
    }

    public UIException(String message) {
        super(message);
    }

    public UIException(String message, Throwable cause) {
        super(message, cause);
    }
}
