package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.IOrderDao;
import com.senla.bookshop.model.Order;
import com.senla.bookshop.util.PropertiesHandler;
import com.senla.bookshop.util.SerializationHandler;
import exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class.getSimpleName());
    private static final OrderDao orderDao = getInstance();
    private List<Order> orders;

    private OrderDao() {
        deserialize();
    }

    public static OrderDao getInstance() {
        return Objects.requireNonNullElse(orderDao, new OrderDao());
    }

    private void deserialize() {
        try {
            var path = PropertiesHandler.getProperties("shop.serialization.path_order");
            orders = (List<Order>) SerializationHandler.readObject(path.get());
            LOGGER.info("Deserialization success");
        } catch (IOException | ClassNotFoundException e) {
            orders = new ArrayList<>();
            LOGGER.error(e);
            throw new DaoException("Deserialization filed.", e);
        }
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public Order getById(Long id) {
        for (Order order : getAll()) {
            if (id.equals(order.getId())) {
                return order;
            }
        }
        LOGGER.error("Order #" + id + " not found.");
        throw new DaoException("Order #" + id + " not found.");
    }

    @Override
    public void delete(Order order) {
        try {
            orders.remove(order);
        } catch (Exception e) {
            throw new DaoException("This order is not available.", e);
        }
    }
}
