package com.senla.store;

import com.senla.exception.ServiceException;
import com.senla.store.api.service.IBookService;
import com.senla.store.api.service.IOrderService;
import com.senla.store.api.service.IRequestService;
import com.senla.store.model.*;
import com.senla.store.service.BookService;
import com.senla.store.service.OrderService;
import com.senla.store.service.RequestService;
import com.senla.store.util.PropertiesHandler;
import com.senla.store.util.SerializationHandler;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Facade {
    private static final Logger LOGGER = Logger.getLogger(Facade.class.getSimpleName());
    private static Facade instance;
    IBookService bookService = BookService.getInstance();
    IOrderService orderService = OrderService.getInstance();
    IRequestService requestService = RequestService.getInstance();

    private Facade() {
    }

    public static Facade getInstance() {
        return Objects.requireNonNullElse(instance, new Facade());
    }

    public void writeOff(long num) {
        Book book = bookService.getBookById(num);
        if (book != null) {
            bookService.writeOff(book);
        } else {
            LOGGER.error("Book is not exist.");
            throw new ServiceException("Filed write  off.");
        }
    }

    public void createOrder(long idBook, int quantity) {
        try {
            orderService.create(new Order(bookService.getBookById(idBook), quantity));
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("The order has not created.");
        }

    }

    public void cancelOrder(long idOrder) {
        try {
            orderService.cancel(orderService.getOrderById(idOrder));
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("The order has not been canceled.");
        }
    }


    public void addBookOnStock(long idBook) {
        try {
            Book book = bookService.getBookById(idBook);
            requestService.addBookOnStock(book);
        } catch (ServiceException e) {
            throw new ServiceException("Can not add book on stock.");
        }
    }


    public void showRequests() {
        try {
            print(requestService.getRequests());
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to view requests.");
        }

    }

    public void showOrders() {
        try {
            print(orderService.getOrders());
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to view orders.");
        }

    }

    public void showBooks() {
        try {
            print(bookService.getBooks());
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to view books.");
        }
    }

    public void changeOrderStatus(long idOrder, int type) {
        try {
            Order order = orderService.getOrderById(idOrder);
            OrderState state = switch (type) {
                case 2 -> OrderState.COMPLETED;
                case 3 -> OrderState.CANCELED;
                default -> OrderState.HOT;
            };
            orderService.changeStatus(order, state);
        } catch (ServiceException e) {
            throw new ServiceException("The status of order has not been canceled.");
        }

    }

    public void createRequest(long id, int quantity) {
        try {
            Book book = bookService.getBookById(id);
            Request request = new Request(book, quantity);
            requestService.createRequest(request);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("The request has not created.");
        }
    }

    public void sortAllBook(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "year";
            case 3 -> "price";
            case 4 -> "status";
            default -> "title";
        };
        try {
            print(bookService.sort(bookService.getBooks(), type));
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Unsuccessful book sorting.");
        }
    }

    public void sortAllOrder(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "price";
            case 3 -> "status";
            default -> "date";
        };
        try {
            print(orderService.sort(orderService.getOrders(), type));
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Unsuccessful orders sorting");
        }
    }

    public void sortAllRequest(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "quantity";
            default -> "title";
        };
        try {
            print(requestService.sort(requestService.getRequests(), type));
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Unsuccessful requests sorting");
        }
    }

    public void amountOfIncome(LocalDateTime date) {
        try {
            int amount = orderService.amountOfIncome(date);
            System.out.println("Amount of money earned " + amount + " $.");
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Filed amount of income.");
        }

    }

    public void showUnpopularBooks() {
        try {
            print(bookService.unpopularBooks());
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to view unpopular books.");
        }

    }

    public void showOrderDetails(long id) {
        try {
            orderService.showDetails(orderService.getOrderById(id));
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to view order details.");
        }

    }

    public void showBookDescription(long id) {
        try {
            Book b = bookService.getBookById(id);
            System.out.println(b);
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to view description.");
        }


    }

    public void print(List list) {
        String name;
        if (list == null || list.isEmpty()) {
            name = "List is empty.";
        } else {
            name = list.get(0).getClass().getSimpleName();
        }
        System.out.println("######################## " + name + " ############################");
        assert list != null;
        list.forEach(System.out::println);
        System.out.print("############################################################\n");
    }

    public void ShowBookRequests() {
        requestService.getRequests().stream()
                .filter(r -> r.getStatus() == RequestStatus.IN_PROCESSING)
                .forEach(r -> System.out.println(r.getMissingBook()));
    }

    public void serializableDao() {
        List<Book> books = bookService.getBooks();
        List<Order> orders = orderService.getOrders();
        List<Request> requests = requestService.getRequests();
        try {
            var path1 = PropertiesHandler.getProperties("shop.serialization.path_books");
            var path2 = PropertiesHandler.getProperties("shop.serialization.path_order");
            var path3 = PropertiesHandler.getProperties("shop.serialization.path_requests");
            if (path1.isPresent()) SerializationHandler.writeObject(path1.get(), books);
            if (path2.isPresent()) SerializationHandler.writeObject(path2.get(), orders);
            if (path3.isPresent()) SerializationHandler.writeObject(path3.get(), requests);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ServiceException("Serialization error.");
        }
        LOGGER.info("Serialization success.");
    }

    public void deserializableDao() {
        try {
            bookService.deserialization();
            orderService.deserialization();
            requestService.deserialization();
        }catch (Exception e ) {
            LOGGER.error(e);
            throw new ServiceException("Deserialization error.");
        }

    }
}





