package com.senla.store.dao;

import com.senla.exception.DaoException;
import com.senla.store.api.dao.IOrderDao;
import com.senla.store.model.Book;
import com.senla.store.model.Order;
import com.senla.store.util.SerializationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDao extends AbstractDao<Order> implements IOrderDao, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class.getSimpleName());
    private static final IOrderDao orderDao = getInstance();
    private List<Order> orders = new ArrayList<>();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return (OrderDao) Objects.requireNonNullElse(orderDao, new OrderDao());
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

    @Override
    public List<Order> deserialization(String pathToFile) {
        try {
            orders = (List<Order>) SerializationHandler.readObject(pathToFile);
            return orders;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new DaoException("Deserialization orders failed.");
        }
    }
}
