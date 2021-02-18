package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.api.service.IOrderService;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.OrderDao;
import by.senla.bookstore.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {

    private final IOrderDao orderDao = OrderDao.getInstance();
    List<Order> list;
    IRequestService requestService = new RequestService();

    @Override
    public List<Order> completedOrders() {
        list = orderDao.getList();
        return list.stream()
                .filter(order -> order.getOrderStatus().equals(OrderState.COMPLETED))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> sort(List<Order> list, String sortBy) {
        switch (sortBy) {
            case "date":
                return list.stream()
                        .sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                        .collect(Collectors.toList());
            case "price":
                return list.stream()
                        .sorted((o1, o2) -> o1.getPrice() - o2.getPrice())
                        .collect(Collectors.toList());
            case "status":
                return list.stream()
                        .sorted((o1, o2) -> o1.getOrderStatus().ordinal() - o2.getOrderStatus().ordinal())
                        .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Order> sortAll(String sortBy) {
        this.list = orderDao.getList();
        return this.sort(list, sortBy);
    }

    @Override
    public void create(Order order) {
        order.setOrderStatus(OrderState.HOT);
        orderDao.save(order);
        for (Book b : order.getBookList()) {
            if (b.getStatus() == BookStatus.MISSING) {
                Request request = new Request();
                request.setMissingBooks(b);
                request.setQuantity(1);
                requestService.sentRequest(request);
            }
        }
    }

    @Override
    public void cancel(Order order) {
        list = orderDao.getList();
        if (list.contains(order)) {
            Order o = list.get(list.indexOf(order));
            o.setOrderStatus(OrderState.CANCELED);
            o.setDate(LocalDateTime.now().plusSeconds(10L));

        }
    }

    @Override
    public void changeStatus(Order order, OrderState state) {
        list = orderDao.getList();
        if (list.contains(order)) {
            Order o = list.get(list.indexOf(order));
            o.setOrderStatus(state);
            o.setDate(LocalDateTime.now().plusSeconds(3l));
        }
    }

    @Override
    public void printAllOrders() {
        list = orderDao.getList();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL ORDERS>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        orderDao.getList().forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void printOrders(List<Order> list) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ORDERS>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        list.forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }
}
