package by.senla.bookstore;

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

public class Runner {
    public static void main(String[] args) {
        InitBD initBD = new InitBD();
        IBookService bookService = new BookService();
        IOrderService orderService = new OrderService();
        IRequestService requestService = new RequestService();

        //Инициализация БД и ее вывод
        initBD.initBookDao();
        bookService.printAllBook();

        //Списать книгу со склада
        bookService.writeOff(BookDao.getInstance().getByID(100000L));
        bookService.printAllBook();

        //Создать заказ на книгу (MISSING)
        orderService.create(new Order(BookDao.getInstance().getByID(100002L)));
        //Создать заказ на книгу (ON_SALE)
        orderService.create(new Order(BookDao.getInstance().getByID(100007L)));
        //еще один
        orderService.printAllOrders();

        //Отменить заказ
        orderService.cancel(OrderDao.getInstance().getByID(500001L));
        //Изменить статус заказа
        orderService.changeStatus(OrderDao.getInstance().getByID(500003L), OrderState.COMPLETED);
        orderService.printAllOrders();

        //Добавить книги на склад
        requestService.printAllRequest();
        requestService.addAllBooksOnSale();
        requestService.printAllRequest();
        bookService.printAllBook();


        //Оставить запрос на книгу
        requestService.sentRequest(new Request(BookDao.getInstance().getByID(100004L)));
        requestService.printAllRequest();
    }
}
