package com.senla.bookshop.service;

import com.senla.bookshop.api.dao.IOrderDao;
import com.senla.bookshop.api.service.IBookService;
import com.senla.bookshop.api.service.IOrderService;
import com.senla.bookshop.api.service.IRequestService;
import com.senla.bookshop.dao.OrderDao;
import com.senla.bookshop.model.*;
import com.senla.bookshop.util.MyRandom;
import exception.DaoException;
import exception.ServiceException;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getSimpleName());
    private static OrderService instance;
    private final IOrderDao orderDao = OrderDao.getInstance();
    IRequestService requestService = RequestService.getInstance();
    IBookService bookService = BookService.getInstance();
    List<Order> orders;

    private OrderService() {
    }

    public static OrderService getInstance() {
        return Objects.requireNonNullElse(instance, new OrderService());
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> completedOrders(LocalDateTime from) {
        orders = orderDao.getAll();
        LOGGER.info("Filter completed orders from " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss")));
        return orders.stream()
                .filter(order -> order.getOrderStatus().equals(OrderState.COMPLETED))
                .filter(order -> from.isBefore(order.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> sort(List<Order> list, String sortBy) {
        if (!list.isEmpty()) {
            switch (sortBy) {
                case "date" -> {
                    LOGGER.info("Sorting by date.");
                    return list.stream()
                            .sorted(Comparator.comparing(Order::getDate))
                            .collect(Collectors.toList());
                }
                case "price" -> {
                    LOGGER.info("Sorting orders by price.");
                    return list.stream()
                            .sorted(Comparator.comparingInt(Order::getPrice))
                            .collect(Collectors.toList());
                }
                case "status" -> {
                    LOGGER.info("Sorting orders by status.");
                    return list.stream()
                            .sorted(Comparator.comparingInt(o -> o.getOrderStatus().ordinal()))
                            .collect(Collectors.toList());
                }
                default -> LOGGER.warn("Invalid input.");
            }
        }
        throw new ServiceException("List is empty.");
    }

    @Override
    public Order getOrderById(long id) {
        try {
            return orderDao.getById(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Order not exist.");
        }
    }

    @Override
    public void create(Order order) {
        order.setOrderStatus(OrderState.HOT);
        orderDao.save(order);
        LOGGER.info("New order #" + order.getId() + " is created.");
        Book b = order.getOrderedBook();
        if (b.getStatus() == BookStatus.MISSING) {
            requestService.createRequest(new Request(b, order.getQuantity()));
        } else if (b.getQuantity() - order.getQuantity() < 0) {
            int dif = order.getQuantity() - b.getQuantity();
            requestService.createRequest(new Request(b, dif));
            bookService.writeOff(b);
        } else {
            b.setQuantity(b.getQuantity() - order.getQuantity());
        }
    }

    @Override
    public void cancel(Order order) {
        orders = orderDao.getAll().stream()
                .filter(o -> o.getOrderStatus() == OrderState.HOT)
                .collect(Collectors.toList());
        if (!orders.isEmpty() && orders.contains(order)) {
            order.setOrderStatus(OrderState.CANCELED);
            order.setDate(MyRandom.getDateChangedOrder());
            Book b = order.getOrderedBook();
            int count = b.getQuantity();
            b.setQuantity(count + order.getQuantity());
        } else {
            LOGGER.warn("No orders or order is completed.");
        }
    }

    @Override
    public int countOrders(LocalDateTime time) {
        LOGGER.info("Counting orders.");
        return completedOrders(time).size();
    }

    @Override
    public int amountOfIncome(LocalDateTime time) {
        try {
            int amount = completedOrders(time).stream()
                    .mapToInt(Order::getPrice)
                    .sum();
            LOGGER.info("Profit calculation.");
            return amount;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ServiceException("List of orders is null");
        }

    }

    @Override
    public void showDetails(Order order) {
        orders = orderDao.getAll();
        if (orders.contains(order)) {
            LOGGER.info("View details of order #" + order.getId() + ".");
            System.out.println(order);
        } else {
            LOGGER.warn("Order not found.");
        }
    }

    @Override
    public void changeStatus(Order order, OrderState state) {
        orders = orderDao.getAll();
        if (orders.contains(order)) {
            order.setOrderStatus(state);
            order.setDate(MyRandom.getDateChangedOrder());
        } else {
            LOGGER.warn("Status has not changed.");
        }
        if (orders.contains(order) && state == OrderState.COMPLETED) {
            Book b = order.getOrderedBook();
            b.setDateOfLastSale(LocalDateTime.now());
        }
    }
}