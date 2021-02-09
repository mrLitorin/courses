package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Order;
import by.senla.bookstore.model.OrderState;

public interface IOrderService {
    void create(Order order);

    void cancel(Order order);

    void changeStatus(Order order, OrderState state);

    void printAllOrders();
}
