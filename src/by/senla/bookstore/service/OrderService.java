package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.api.service.IBookService;
import by.senla.bookstore.api.service.IOrderService;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.OrderDao;
import by.senla.bookstore.model.*;
import by.senla.bookstore.util.MyRandom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
    private static OrderService instance;
    private final IOrderDao orderDao = OrderDao.getInstance();
    List<Order> orders;
    IRequestService requestService = RequestService.getInstance();
    IBookService bookService = BookService.getInstance();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return Objects.requireNonNullElse(instance, new OrderService());
    }

    @Override
    public int countOrders(LocalDateTime time) {
        return completedOrders(time).size();
    }

    @Override
    public int amountOfIncome(LocalDateTime time) {
        return completedOrders(time).stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    @Override
    public List<Order> completedOrders(LocalDateTime from) {
        System.out.println("FROM >>> " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss")));
        orders = orderDao.getAll();
        return orders.stream()
                .filter(order -> order.getOrderStatus().equals(OrderState.COMPLETED))
                .filter(order -> from.isBefore(order.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> sort(List<Order> list, String sortBy) {
        if (!list.isEmpty()) {
            switch (sortBy) {
                case "date":
                    return list.stream()
                            .sorted(Comparator.comparing(Order::getDate))
                            .collect(Collectors.toList());
                case "price":
                    return list.stream()
                            .sorted(Comparator.comparingInt(Order::getPrice))
                            .collect(Collectors.toList());
                case "status":
                    return list.stream()
                            .sorted(Comparator.comparingInt(o -> o.getOrderStatus().ordinal()))
                            .collect(Collectors.toList());
                default:
                    System.out.println("Invalid input");
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Order> sortAll(String sortBy) {
        this.orders = orderDao.getAll();
        return this.sort(orders, sortBy);
    }

    @Override
    public void create(Order order) {
        order.setOrderStatus(OrderState.HOT);
        orderDao.save(order);
        Map<Book, Integer> map = order.getBookMap();
        for (Book b : map.keySet()) {
            if (b.getStatus() == BookStatus.MISSING) {
                requestService.sentRequest(new Request(b, map.get(b)));
            } else if (b.getQuantity() - map.get(b) < 0) {
                int dif = map.get(b) - b.getQuantity();
                requestService.sentRequest(new Request(b, dif));
                bookService.writeOff(b);
            } else {
                b.setQuantity(b.getQuantity() - map.get(b));
            }
        }
    }

    @Override
    public void showDetails(Order order) {
        orders = orderDao.getAll();
        if (orders.contains(order)) {
            System.out.println(order);
        }
    }

    @Override
    public Order getOrderById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public void cancel(Order order) {
//        orders = orderDao.getAll();
        orders = orderDao.getAll().stream()
                .filter(o -> o.getOrderStatus() == OrderState.HOT)
                .collect(Collectors.toList());
        if (!orders.isEmpty() && orders.contains(order)) {
            order.setOrderStatus(OrderState.CANCELED);
            order.setDate(MyRandom.getDateChangedOrder());
            Map<Book, Integer> map = order.getBookMap();
            for (Book b : map.keySet()) {
                int res = b.getQuantity();
                b.setQuantity(res + map.get(b));
            }
        } else {
            System.out.println("No orders or order is completed.");
        }
    }

    @Override
    public void changeStatus(Order order, OrderState state) {
        orders = orderDao.getAll();
        if (orders.contains(order)) {
            order.setOrderStatus(state);
            order.setDate(MyRandom.getDateChangedOrder());
        }
        if (orders.contains(order) && state == OrderState.COMPLETED) {
            Map<Book, Integer> map = order.getBookMap();
            for (Book b : map.keySet()) {
                b.setDateOfLastSale(LocalDateTime.now());
            }
        }
    }

    @Override
    public void printAllOrders() {
        orders = orderDao.getAll();
        System.out.println("######################## ALL ORDERS #######################");
        if (!orders.isEmpty()) {
            orderDao.getAll().forEach(System.out::println);
        } else {
            System.out.println("Order not found.");
        }
        System.out.print("###########################################################\n");
    }

    @Override
    public void printOrders(List<Order> orders) {
        System.out.println("####################### ORDERS ############################");
        if (!orders.isEmpty()) {
            orders.forEach(System.out::println);
        }
        System.out.print("###########################################################\n");
    }
}