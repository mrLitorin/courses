package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.api.service.IOrderService;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.OrderDao;
import by.senla.bookstore.model.*;

import java.util.List;

public class OrderService implements IOrderService {

    private final IOrderDao orderDao = OrderDao.getInstance();
    List<Order> list = orderDao.getList();
    IRequestService requestService = new RequestService();

    @Override
    public void create(Order order) {
        order.setOrderStatus(OrderState.HOT);
        orderDao.save(order);
        for (Book b : order.getBookList()) {
            if (b.getStatus() == BookStatus.MISSING) {
                Request request = new Request();
                request.setMissingBooks(b);
                requestService.sentRequest(request);
            } 
        }
    }

    @Override
    public void cancel(Order order) {
        if (list.contains(order)) {
            Order o = list.get(list.indexOf(order));
            o.setOrderStatus(OrderState.CANCELED);
        }
    }

    @Override
    public void changeStatus(Order order, OrderState state) {
        if (list.contains(order)) {
            Order o = list.get(list.indexOf(order));
            o.setOrderStatus(state);
        }
    }

    @Override
    public void printAllOrders() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL ORDERS>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        orderDao.getList().forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }
}
