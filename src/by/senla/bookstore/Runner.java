package by.senla.bookstore;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.api.dao.IOrderDao;
import by.senla.bookstore.api.service.IBookService;
import by.senla.bookstore.api.service.IOrderService;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.dao.OrderDao;
import by.senla.bookstore.model.*;
import by.senla.bookstore.service.BookService;
import by.senla.bookstore.service.OrderService;
import by.senla.bookstore.service.RequestService;
import by.senla.bookstore.util.InitBD;

import java.time.LocalDateTime;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        InitBD initBD = new InitBD();
        IBookService bookService = BookService.getInstance();
        IOrderService orderService = OrderService.getInstance();
        IRequestService requestService = RequestService.getInstance();
        IBookDao bookDao = BookDao.getInstance();
        IOrderDao orderDao = OrderDao.getInstance();

        //Инициализация БД и ее вывод
        initBD.initBD();

        //Списать книгу со склада
        Book book1 = bookService.getBookById(100000L);
        bookService.writeOff(book1);
        //Создать заказ
        Order order1 = new Order(bookService.getBookById(100001L), 3);
        order1.setClient(new Client("Ivanov Ivan", 25));
        Order order2 = new Order(bookService.getBookById(100002L), 3);
        order2.setClient(new Client("Petrov Petr", 23));
        Order order3 = new Order(bookService.getBookById(100004L), 2);
        order3.setClient(new Client("Petrov Petr", 23));
        orderService.create(order1);
        orderService.create(order2);
        orderService.create(order3);

        //Отменить заказ
        orderService.cancel(orderService.getOrderById(500000L));
        //Сменить статус
        orderService.changeStatus(order2, OrderState.COMPLETED);
        orderService.changeStatus(order1, OrderState.COMPLETED);
        orderService.changeStatus(order3, OrderState.COMPLETED);
        //добавить на склад
        requestService.addBookOnStock(bookService.getBookById(100002L));
        //Оставить запрос на книгу
        requestService.createRequest(new Request(bookService.getBookById(100008), 3));
        requestService.createRequest(new Request(bookService.getBookById(100007), 2));


        // ВЫВОД КНИГ
        requestService.getRequests();                                           // ВЫВОД ЗАПРОСОВ
        orderService.getOrders();                                                     // ВЫВОД ЗАКАЗОВ
        //Список книг (сортировать по алфавиту, дате издания, цене, наличию на складе)
        bookService.sort(bookService.getBooks(), "title");
        bookService.sort(bookService.getBooks(), "year");
        bookService.sort(bookService.getBooks(), "price");
        bookService.sort(bookService.getBooks(), "status");
        //Список заказов (сортировать по дате исполнения, цене, статусу)
        orderService.sort(orderService.getOrders(), "date");
        orderService.sort(orderService.getOrders(), "price");
        orderService.sort(orderService.getOrders(), "status");
        //Список запросов на книгу (сортировать по количеству запросов, алфавиту)
        requestService.sort(requestService.getRequests(), "quantity");
        requestService.sort(requestService.getRequests(), "title");
        //Список выполненных заказов за период времени (сортировать по дате, цене)
        List<Order> completedOrders = orderService.completedOrders(LocalDateTime.now().plusSeconds(70));
        orderService.getOrders();
        orderService.getOrders();
        //Сумму заработанных средств за период времени
        System.out.println(orderService.amountOfIncome(LocalDateTime.now().plusSeconds(70)));
        //Количество выполненных заказов за период времени
        System.out.println(orderService.countOrders(LocalDateTime.now().plusSeconds(70)));
        //Список «залежавшихся» книг не проданы больше чем 6 мес. (сортировать по датепоступления, цене)
        bookService.unpopularBooks();
        //Посмотреть детали заказа (какие-либо данные заказчика + книги)
        orderService.showDetails(orderService.getOrderById(500000));
        //Посмотреть описание книги
        System.out.println(bookService.getBookById(100001L));
    }
}