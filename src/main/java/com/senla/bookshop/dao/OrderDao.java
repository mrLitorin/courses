package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.IOrderDao;
import com.senla.bookshop.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {
    private static final OrderDao orderDao = getInstance();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return Objects.requireNonNullElse(orderDao, new OrderDao());
    }

    public List<Order> getOrders() {
        return new ArrayList<>(getInstance().getAll());
    }
}
