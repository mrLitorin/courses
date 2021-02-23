package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {
    private static final OrderDao orderDao = new OrderDao();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return orderDao;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orderDao.getAll());
    }

    @Override
    public Order update(Order order) {
        List<Order> orders = orderDao.getAll();
        Order temp = null;
        if (orders.contains(order)) {
            temp = orders.get(orders.indexOf(order));
            temp.setId(order.getId());
            temp.setOrderStatus(order.getOrderStatus());
            temp.setClient(order.getClient());
        }
        return temp;
    }
}
