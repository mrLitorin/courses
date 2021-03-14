package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.model.Order;

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
