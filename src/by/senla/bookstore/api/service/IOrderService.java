package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Order;
import by.senla.bookstore.model.OrderState;

import java.util.List;

public interface IOrderService {
    void create(Order order);

    void cancel(Order order);

    void changeStatus(Order order, OrderState state);

    void printAllOrders();

    void printOrders(List<Order> list);

    List<Order> sort(List<Order> list, String sortBy);

    List<Order> sortAll(String sortBy);

    List<Order> completedOrders();

}
