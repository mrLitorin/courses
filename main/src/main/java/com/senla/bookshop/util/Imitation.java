package com.senla.bookshop.util;

import com.senla.bookshop.api.dao.IBookDao;
import com.senla.bookshop.api.service.IBookService;
import com.senla.bookshop.api.service.IOrderService;
import com.senla.bookshop.api.service.IRequestService;
import com.senla.bookshop.dao.BookDao;
import com.senla.bookshop.model.*;
import com.senla.bookshop.service.BookService;
import com.senla.bookshop.service.OrderService;
import com.senla.bookshop.service.RequestService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Objects;

public class Imitation {
    private static Imitation instance;
    private static final Logger LOGGER = Logger.getLogger(Imitation.class.getSimpleName());


    private Imitation() {
        LOGGER.info("Start imitation.");
        try {
            start();
            LOGGER.info("End of imitation.");
        } catch (Exception e) {
            LOGGER.error("Imitation failed.");
        }
    }

    public static Imitation getInstance() {
        return Objects.requireNonNullElse(instance, new Imitation());
    }

    public static void start() {
        IBookService bookService = BookService.getInstance();
        IOrderService orderService = OrderService.getInstance();
        IRequestService requestService = RequestService.getInstance();

        //Инициализация БД
        {
            IBookDao bookDao = BookDao.getInstance();

            bookDao.save(new Book("Title1", "Author1", 3));// 100000
            bookDao.save(new Book("Title2", "Author2", 4));// 100001
            bookDao.save(new Book("Title3", "Author3", 0));// 100002 MISSING
            bookDao.save(new Book("Title4", "Author4", 2));// 100003
            bookDao.save(new Book("Title0", "Author0", 5));// 100004
            bookDao.save(new Book("Title6", "Author6", 0));// 100005 MISSING
            bookDao.save(new Book("Title7", "Author7", 5));// 100006
            bookDao.save(new Book("Title8", "Author8", 7));// 100007
            bookDao.save(new Book("Title9", "Author9", 0));// 100008 MISSING
            //рандомное время для книг
            bookDao.getAll()
                    .forEach(book -> book.setDateOfLastSale(MyRandom.getDateOfLastSale()));
            //о книге
            bookDao.getAll()
                    .forEach(book -> book.setDescription("About the book.....bla bla"));
        }


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
        orderService.completedOrders(LocalDateTime.now().plusSeconds(70));
        //Сумму заработанных средств за период времени
        int amount = orderService.amountOfIncome(LocalDateTime.now().plusSeconds(70));
        System.out.println("Amount of money earned " + amount + " $.");
        //Количество выполненных заказов за период времени
        int quantity = orderService.countOrders(LocalDateTime.now().plusSeconds(70));
        System.out.println("number of completed orders is " + quantity);
        //Список «залежавшихся» книг не проданы больше чем 6 мес. (сортировать по датепоступления, цене)
        bookService.unpopularBooks();
        //Посмотреть детали заказа (какие-либо данные заказчика + книги)
        orderService.showDetails(orderService.getOrderById(500000));
        //Посмотреть описание книги
        System.out.println(bookService.getBookById(100001L));
    }
}