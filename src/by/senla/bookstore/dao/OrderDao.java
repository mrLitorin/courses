package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {
    private static final OrderDao orderDao = new OrderDao();
    private final List<Order> orders = super.getList();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return orderDao;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Order update(Order order) {
        Order temp = null;
        if (orders.contains(order)) {
            temp = orders.get(orders.indexOf(order));
            temp.setId(order.getId());
            temp.setOrderStatus(order.getOrderStatus());
            temp.setClient(order.getClient());
        }
        return temp;
    }

    @Override
    public List<Order> getList() {
        List<Order> list = new ArrayList<Order>(orders);
        return list;
    }
}
