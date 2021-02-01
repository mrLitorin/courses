package by.senla.task3.ex4.util;

public class GeneratorID {
    private static Long bookId = 100000L;
    private static Long orderId = 500000L;
    private static Long requestId = 900000L;
    private static Long clientId = 1300000L;

    public static Long generateBookId() {
        return bookId++;
    }

    public static Long generateOrderId() {
        return orderId++;
    }

    public static Long generateRequestId() {
        return requestId++;
    }

    public static Long generateClientId() {
        return clientId++;
    }
}
