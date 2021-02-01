package by.senla.task3.ex4.api.service;

import by.senla.task3.ex4.model.Order;
import by.senla.task3.ex4.model.OrderState;

public interface IOrderService {
    void create(Order order);

    void cancel(Order order);

    void changeStatus(Order order, OrderState state);

    void printAllOrders();
}
