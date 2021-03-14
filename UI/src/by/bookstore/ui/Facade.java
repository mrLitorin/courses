package by.bookstore.ui;

import by.senla.bookstore.model.*;
import by.senla.bookstore.service.BookService;
import by.senla.bookstore.service.OrderService;
import by.senla.bookstore.service.RequestService;

import java.time.LocalDateTime;
import java.util.List;
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
        requestService.addBookOnStock(bookService.getBookById(idBook));
    }

    public void showRequests() {
        print(requestService.getRequests());
    }

    public void showOrders() {
        print(orderService.getOrders());
    }

    public void showBooks() {
        print(bookService.getBooks());
    }

    public void changeOrderStatus(long idOrder, int type) {
        Order order = orderService.getOrderById(idOrder);
        OrderState state = switch (type) {
            case 2 -> OrderState.COMPLETED;
            case 3 -> OrderState.CANCELED;
            default -> OrderState.HOT;
        };
        orderService.changeStatus(order, state);
    }

    public void createRequest(long id, int quantity) {
        Book book = bookService.getBookById(id);
        Request request = new Request(book, quantity);
        requestService.createRequest(request);
    }

    public void sortAllBook(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "year";
            case 3 -> "price";
            case 4 -> "status";
            default -> "title";
        };
        print(bookService.sort(bookService.getBooks(), type));
    }

    public void sortAllOrder(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "price";
            case 3 -> "status";
            default -> "date";
        };
        print(orderService.sort(orderService.getOrders(), type));
    }

    public void sortAllRequest(int sortBy) {
        String type = switch (sortBy) {
            case 2 -> "quantity";
            default -> "title";
        };
        print(requestService.sort(requestService.getRequests(), type));
    }

    public void amountOfIncome(LocalDateTime date) {
        System.out.println(orderService.amountOfIncome(date));
    }

    public void showUnpopularBooks() {
        print(bookService.unpopularBooks());
    }

    public void showOrderDetails(long id) {
        orderService.showDetails(orderService.getOrderById(id));
    }

    public void showBookDescription(long id) {
        Book b = bookService.getBookById(id);
        System.out.println(b);
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
}





