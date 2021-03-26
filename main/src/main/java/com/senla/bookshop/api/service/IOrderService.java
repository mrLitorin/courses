package com.senla.bookshop.api.service;

import com.senla.bookshop.model.Order;
import com.senla.bookshop.model.OrderState;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    List<Order> getOrders();

    List<Order> sort(List<Order> list, String sortBy);

    List<Order> completedOrders(LocalDateTime from);

    Order getOrderById(long id);

    int amountOfIncome(LocalDateTime time);

    int countOrders(LocalDateTime time);

    void create(Order order);

    void cancel(Order order);

    void changeStatus(Order order, OrderState state);

    void showDetails(Order order);
}
