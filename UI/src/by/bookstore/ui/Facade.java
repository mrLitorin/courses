package by.bookstore.ui;

import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.Order;
import by.senla.bookstore.model.OrderState;
import by.senla.bookstore.model.Request;
import by.senla.bookstore.service.BookService;
import by.senla.bookstore.service.OrderService;
import by.senla.bookstore.service.RequestService;

import java.time.LocalDateTime;
import java.util.Objects;

public class Facade {
    private static Facade instance;
    BookService bookService = BookService.getInstance();
    OrderService orderService = OrderService.getInstance();
    RequestService requestService = RequestService.getInstance();

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
            System.out.println("Book is not exist. ");
        }
    }

    public void createOrder(long idBook, int quantity) {
        orderService.create(new Order(bookService.getBookById(idBook), quantity));
    }

    public void cancelOrder(long idOrder) {
        orderService.cancel(orderService.getOrderById(idOrder));
    }


    public void addBookOnStock(long idBook) {
        bookService.addBookOnStock(bookService.getBookById(idBook));
    }

    public void printAllRequest() {
        requestService.printAllRequest();
    }

    public void printAllOrder() {
        orderService.printAllOrders();
    }

    public void printAllBook() {
        bookService.printAllBook();
    }

    public void changeOrderStatus(long idOrder, int type) {
        Order order = orderService.getOrderById(idOrder);
        OrderState state = switch (type) {
            case 1 -> OrderState.HOT;
            case 2 -> OrderState.COMPLETED;
            case 3 -> OrderState.CANCELED;
            default -> null;
        };
        orderService.changeStatus(order, state);
    }

    public void sentRequest(long id, int quantity) {
        Book book = bookService.getBookById(id);
        Request request = new Request(book, quantity);
        requestService.sentRequest(request);
    }

    public void sortAllBook(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "year";
            case 3 -> "price";
            case 4 -> "status";
            default -> "title";
        };
        bookService.printBooks(bookService.sortAll(type));
    }

    public void sortAllOrder(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "price";
            case 3 -> "status";
            default -> "date";
        };
        orderService.printOrders(orderService.sortAll(type));
    }

    public void sortAllRequest(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "quantity";
            default -> "title";
        };
        requestService.printRequest(requestService.sortAll(type));
    }

    public void amountOfIncome(LocalDateTime date) {
        System.out.println(orderService.amountOfIncome(date));

    }

    public void unpopularBooks() {
        bookService.printBooks(bookService.unpopularBooks());
    }

    public void showOrderDetails(long id) {
        orderService.showDetails(orderService.getOrderById(id));
    }

    public void showBookDescription(long id) {
        bookService.showDescription(bookService.getBookById(id));
    }
}
